export function string2Date(value) {
    let year = value.substr(0, 4)
    let month = value.substr(5, 2)
    let day = value.substr(8, 2)
    let hour = value.substr(11, 2)
    let min = value.substr(14, 2)
    let second = value.substr(17,2)
    return new Date(year,month-1,day,hour,min,second)
}

export function dateFormat(date) {
    let year = date.getFullYear();                // 年
    let month = showTime(date.getMonth() + 1);        // 月
    let day = showTime(date.getDate());          // 日
    let hours = showTime(date.getHours());         // 小时
    let minutes = showTime(date.getMinutes());    // 分钟
    let second = showTime(date.getSeconds());     // 秒
    let str = '';
    str = str + year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + second;
    return str;
}

function showTime(t) {
    let time
    time = t >= 10 ? t : '0' + t
    return time
}