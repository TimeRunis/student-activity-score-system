package com.sass.studentactivityscoresystem.controller;

import com.sass.studentactivityscoresystem.controller.method.DeleteController;
import com.sass.studentactivityscoresystem.controller.method.GetController;
import com.sass.studentactivityscoresystem.controller.method.PostController;
import com.sass.studentactivityscoresystem.controller.method.PutController;
import com.sass.studentactivityscoresystem.entity.RespBody;
import com.sass.studentactivityscoresystem.entity.ScoreCode;
import com.sass.studentactivityscoresystem.service.ScoreCodeService;
import com.sass.studentactivityscoresystem.service.UserScoreService;
import com.sass.studentactivityscoresystem.utils.JwtUtils;
import com.sass.studentactivityscoresystem.utils.TimeTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@Scope("prototype")
@CrossOrigin(origins = "*")
public class ScoreCodeController extends BaseController implements GetController, PostController, DeleteController, PutController {
    private ScoreCodeService scoreCodeService;
    private UserScoreService userScoreService;
    private List<ScoreCode> list;
    private ScoreCode scoreCode;

    @Autowired
    public ScoreCodeController(ScoreCodeService scoreCodeService, UserScoreService userScoreService, List<ScoreCode> list, ScoreCode scoreCode) {
        this.scoreCodeService = scoreCodeService;
        this.userScoreService = userScoreService;
        this.list = list;
        this.scoreCode = scoreCode;
    }

    @GetMapping("/scoreCode")
    @Override
    public RespBody doGet(Map<Object, String> map, HttpServletRequest request) {
        if(!map.isEmpty()){
            if(JwtUtils.checkPermission(request.getHeader("token"),9)){
                //尝试获取分页参数来全部查询(管理员)
                if(map.containsKey("current")&&map.containsKey("size")){
                    rep.setResp(0,scoreCodeService.findAll(map.get("current"),map.get("size")),"查询成功");
                }else if(map.containsKey("code")){
                    //获取失败则进行单个查询
                    rep.setResp(0,scoreCodeService.getOneByPage(map.get("code")),"查询成功");
                }
            }else {
                if(map.containsKey("code")){
                    //非管理员用户查询
                    scoreCode=scoreCodeService.getById(map.get("code"));
                    if (scoreCode!=null){
                        map.put("number",scoreCode.getNumber().toString());
                        map.put("deadLine", scoreCode.getDeadLine().toString());
                        map.put("isUsed",scoreCode.getUsed().toString());
                        rep.setResp(0,map,"查询成功");
                    }else {
                        rep.setResp(-1,null,"这不是一个有效的兑换码");
                    }
                }
            }

        }else {
            rep.setResp(-1,null,"空参数");
        }
        return rep;
    }

    @PostMapping("/scoreCode")
    @Override
    public RespBody doPost(Map<Object, String> map, HttpServletRequest request) {
        //空参数检查
        if(!map.isEmpty()){
            //权限检查
            if(JwtUtils.checkPermission(request.getHeader("token"),9)){
                try{
                    //检查参数
                    int number = Integer.parseInt(map.get("number"));
                    int score = Integer.parseInt(map.get("score"));
                    Date deadLine = TimeTransformer.string2Date(map.get("deadLine"));
                    //单生成
                    if(number==1){
                        scoreCode.setCode(scoreCodeService.generateCode());
                        scoreCode.setNumber(score);
                        scoreCode.setUserId(null);
                        scoreCode.setCreatorId(JwtUtils.getUserId(request.getHeader("token")));
                        scoreCode.setCreateTime(new Date());
                        scoreCode.setDeadLine(deadLine);
                        scoreCode.setUseTime(null);
                        scoreCode.setUsed(false);
                        scoreCodeService.getBaseMapper().insert(scoreCode);
                        rep.setResp(0,scoreCode,"生成成功");
                    }else if(number>1){
                        //多生成
                        list.clear();
                        for (int i=0;i<number;i++){
                            scoreCode.setCode(scoreCodeService.generateCode());
                            scoreCode.setNumber(score);
                            scoreCode.setUserId(null);
                            scoreCode.setCreatorId(JwtUtils.getUserId(request.getHeader("token")));
                            scoreCode.setCreateTime(new Date());
                            scoreCode.setDeadLine(deadLine);
                            scoreCode.setUseTime(null);
                            scoreCode.setUsed(false);
                            scoreCodeService.getBaseMapper().insert(scoreCode);
                            list.add(scoreCode.clone());
                        }
                        rep.setResp(0,list,"生成成功");
                    }
                }catch (Exception e){
                    rep.setResp(-1,null,"非法参数");
                }

            }
        }else {
            rep.setResp(-1,null,"空参数");
        }
        return rep;
    }

    @DeleteMapping("/scoreCode")
    @Override
    public RespBody doDelete(Map<Object, String> map, HttpServletRequest request) {
        //空参数检查
        if(!map.isEmpty()){
            //权限检查
            if(JwtUtils.checkPermission(request.getHeader("token"),9)){
                try {
                    //获取参数
                    String code =map.get("code");
                    if(scoreCodeService.getById(code)!=null){
                        scoreCodeService.removeCode(code);
                        rep.setResp(0,null,"删除成功");
                    }else {
                        rep.setResp(-1,null,"不存在的兑换码");
                    }
                }catch (Exception e){
                    rep.setResp(-1,null,"非法参数");
                }
            }
        }else {
            rep.setResp(-1,null,"空参数");
        }
        return rep;
    }

    @PutMapping("/scoreCode")
    @Override
    public RespBody doPut(Map<Object, String> map, HttpServletRequest request) {
        //空参数
        if(!map.isEmpty()){
            try{
                //获取参数
                String code =map.get("code");
                scoreCode=scoreCodeService.getById(code);
                //code是否有效
                if(scoreCode!=null&&!scoreCode.getUsed()&&scoreCode.getDeadLine().compareTo(new Date())>=0){
                    //增加积分
                    userScoreService.addUserScore(JwtUtils.getUserId(request.getHeader("token")),scoreCode.getNumber());
                    //更新code状态
                    scoreCode.setUsed(true);
                    scoreCode.setUserId(JwtUtils.getUserId(request.getHeader("token")));
                    scoreCode.setUseTime(new Date());
                    scoreCodeService.updateById(scoreCode);
                    rep.setResp(0,null,"兑换成功!");
                }else if(scoreCode==null){
                    rep.setResp(-1,null,"不存在的兑换码");
                }else if(scoreCode.getUsed()){
                    rep.setResp(-1,null,"已使用的兑换码");
                }else if(scoreCode.getDeadLine().compareTo(new Date())<0){
                    rep.setResp(-1,null,"已过期的兑换码");
                }
            }catch (Exception e){
                rep.setResp(-1,null,"非法参数");
                e.printStackTrace();
            }
        }else {
            rep.setResp(-1,null,"空参数");
        }
        return rep;
    }

}
