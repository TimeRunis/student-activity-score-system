import axios from "axios";
import {Notify} from "vant";
import {api} from "../../public/ApiList";

function getCookie(cname) {
    let name = cname + "=";
    let ca = document.cookie.split(";");
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i].trim();
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
}

//请求拦截器
axios.interceptors.request.use(function (config) {
    let token = getCookie("token")
    if (token) {
        // 设置请求头
        config.headers.set({
            "Content-Type":"application/json",
            "token":token
        })
    }
    return config
}, function (error) {
    //请求出错
    return Promise.reject(error);
});


function data2url(apiId,data){
    let url=api[apiId]+"?"
    for(let i in data){
        url+=i+"="+data[i]+"&"
    }
    return url
}

export function message(resp){
    if(resp["code"]===0){
        Notify({ type: 'success', message: resp["message"] });
    }else {
        Notify({ type: 'danger', message: resp["message"] });
    }
}

export function apiUpload(apiId,file){
    try{
        let tempAxios=axios.create();
        return tempAxios.post(api[apiId],file,{headers:{"Content-Type":"multipart/form-data","token":getCookie("token")}});
    }catch (e){
        Notify({type:"danger",message:"服务器错误"})
    }
}

export function apiPost(apiId,data){
    try{
        return axios.post(api[apiId],data);
    }catch (e){
        Notify({type:"danger",message:"服务器错误"})
    }
}

export function apiGet(apiId,data){
    try{
        return axios.get(data2url(apiId,data))
    }catch (e){
        Notify({type:"danger",message:"服务器错误"})
    }
}

export function apiPut(apiId,data){
    try{
        return axios.put(api[apiId],data);
    }catch (e){
        Notify({type:"danger",message:"服务器错误"})
    }
}

export function apiDelete(apiId,data){
    try{
        return axios.delete(data2url(apiId,data));
    }catch (e){
        Notify({type:"danger",message:"服务器错误"})
    }
}