/**
 * 
 */

// servlet 을 개별로 만드는 것은 MVC 패턴이 아니다. 
// 하나의 servlet 이 모든 요청을 받아서 해결한다. 

// .jsp 의 contextPath 를 그대로 사용할 수 없음. 
// => .js 안에서의 contextPath는 별도로 생성해야 한다. 
const getContextPath = () => {
  const host = location.host; /* localhost:8080 */
  const url = location.href;  /* http://localhost:8080/mvc/getDate.do */
  const begin = url.indexOf(host) + host.length;
  const end = url.indexOf('/', begin + 1);
  return url.substring(begin, end);
}

const getDateTime = () => {
  const type = document.getElementById('type');
  if (type.value === 'date') {
    location.href = getContextPath() + '/getDate.do';
  } else if (type.value === 'time') {
    location.href = getContextPath() + '/getTime.do';
  } else if (type.value === 'datetime') {
    location.href = getContextPath() + '/getDateTime.do';
  }
}
// prefix <-> suffix  
document.getElementById('btn').addEventListener('click', getDateTime);
