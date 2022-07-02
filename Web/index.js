let date = new Date();

const renderCalender = () => {
  const viewYear = date.getFullYear();
  const viewMonth = date.getMonth();

  document.querySelector('.year-month').textContent = `${viewYear}년 ${viewMonth + 1}월`;

  const prevLast = new Date(viewYear, viewMonth, 0);
  const thisLast = new Date(viewYear, viewMonth + 1, 0);

  const PLDate = prevLast.getDate(); //지난달 마지막 날짜
  const PLDay = prevLast.getDay();   //지난달 마지막 요일

  const TLDate = thisLast.getDate();  //이번달 마지막 날짜
  const TLDay = thisLast.getDay();    //이번달 마지막 요일

  //Dates 기본 배열
  const prevDates = [];
  const thisDates = [...Array(TLDate + 1).keys()].slice(1); //0~n-1까지 배열이므로 마지막 날짜+1 = n, 앞에 0을 없애기 위해 slice 메서드 사용
  const nextDates = [];

  //preDates 계산
  if (PLDay !== 6) {   //지난달 마지막 요일이 토요일이면 그릴 필요 없음
    
    //0~지난달 마지막 요일 반복
    for (let i = 0; i < PLDay + 1; i++) {
      //마지막 날짜부터 1씩 줄어든 값을 unshift메서드를 통해 preDates 배열 앞쪽에 채워넣음
      prevDates.unshift(PLDate - i);
    }
  }


  //nextDates 계산 
  //다음 달 날짜 (이번달 마지막 요일 기준 필요한 개수 파악하여 1부터 1씩 증가)
  for (let i = 1; i < 7 - TLDay; i++) {
    nextDates.push(i);
  }

  //Dates 합치기
  const dates = prevDates.concat(thisDates, nextDates);
  const firstDateIndex = dates.indexOf(1);
  const lastDateIndex = dates.lastIndexOf(TLDate);


  //Dates 정리
  dates.forEach((date, i) => {
    const condition = i >= firstDateIndex && i < lastDateIndex + 1
                      ? 'this'
                      : 'other';
    dates[i] = `<div class="date"><span class=${condition}>${date}</span></div>`;
  });

   // Dates 그리기
  document.querySelector('.dates').innerHTML = dates.join('');

  const today = new Date();
  if (viewMonth === today.getMonth() && viewYear === today.getFullYear()) {
    for (let date of document.querySelectorAll('.this')) {
      if (+date.innerText === today.getDate()) {
        date.classList.add('today');
        break;
      }
    }
  }
};

renderCalender();

const prevMonth = () => {
  date.setMonth(date.getMonth() - 1);
  renderCalender();
};

const nextMonth = () => {
  date.setMonth(date.getMonth() + 1);
  renderCalender();
};

const goToday = () => {
  date = new Date();
  renderCalender();
};


// 과제리스트
const reportListElem = document.querySelector(".report-list");
const reportInputElem = document.querySelector('.report-input');

let id_report = 0;
let reports = [];

const setReports = (newReports) => {
  reports = newReports;
}

const getAllReports = () => {
  return reports;
}

const appendReports = (text) => {
  const newId = id_report++;
  const newReports = getAllReports().concat({id: newId, isCompleted: false, content: text })
  setReports(newReports);
  paintReport();
}

const deleteReport = (reportId) => {
  const newReports = getAllReports().filter(report => report.id !== reportId);
  setReports(newReports);
  paintReport();
}

const completeReport = (reportId) => {
  const newReports = getAllReports().map(report => report.id === reportId ? {...report, isCompleted: !report.isCompleted} : report);
  setReports(newReports);
  paintReport();
}

const paintReport = () => {
  reportListElem.innerHTML = null;
  const allReports = getAllReports();

  allReports.forEach(report => {
    const reportItemElem = document.createElement('li');
    reportItemElem.classList.add('report-item');

    reportItemElem.setAttribute('data-id', report.id);

    const checkboxElem = document.createElement('div');
    checkboxElem.classList.add('checkbox');
    checkboxElem.addEventListener('click', () => completeReport(report.id));

    const reportElem = document.createElement('div');
    reportElem.classList.add('report');
    reportElem.innerText = report.content;

    const delBtnElem = document.createElement('button');
    delBtnElem.classList.add('delBtn');
    delBtnElem.addEventListener('click', () => deleteReport(report.id))
    delBtnElem.innerHTML = 'X';

    if(report.isCompleted) {
      reportItemElem.classList.add('checked');
      checkboxElem.innerText = '✔'
    }

    reportItemElem.appendChild(checkboxElem);
    reportItemElem.appendChild(reportElem);
    reportItemElem.appendChild(delBtnElem);

    reportListElem.appendChild(reportItemElem);
  })
}

const initReport = () => {
  reportInputElem.addEventListener('keypress', (e) =>{
    if( e.key === 'Enter' ){
      appendReports(e.target.value); reportInputElem.value='';
    }
  });
}


// todo
const todoInputElem = document.querySelector('.todo-input');
const todoListElem = document.querySelector('.todo-list');
const completeAllBtnElem = document.querySelector('.complete-all-btn');
const leftItemsElem = document.querySelector('.left-items')
const showAllBtnElem = document.querySelector('.show-all-btn');
const showActiveBtnElem = document.querySelector('.show-active-btn');
const showCompletedBtnElem = document.querySelector('.show-completed-btn');
const clearCompletedBtnElem = document.querySelector('.clear-completed-btn');

let id_todo = 0;
const setId = (newId) => {id_todo = newId};

let isAllCompleted = false; // 전체 todos 체크 여부
const setIsAllCompleted = (bool) => { isAllCompleted = bool};

let currentShowType = 'all'; // all  | active | complete
const setCurrentShowType = (newShowType) => currentShowType = newShowType

let todos = [];
const setTodos = (newTodos) => {
    todos = newTodos;
}

const getAllTodos = () => {
    return todos;
}
const getCompletedTodos = () => {
    return todos.filter(todo => todo.isCompleted === true );
}

const getActiveTodos = () => {
  return todos.filter(todo => todo.isCompleted === false);
}

const setLeftItems = () => {
  const leftTodos = getActiveTodos()
  leftItemsElem.innerHTML = `${leftTodos.length} items left`
}

const completeAll = () => {
  completeAllBtnElem.classList.add('checked');
  const newTodos = getAllTodos().map(todo => ({...todo, isCompleted: true }) )
  setTodos(newTodos)
}

const incompleteAll = () => {
  completeAllBtnElem.classList.remove('checked');
  const newTodos =  getAllTodos().map(todo => ({...todo, isCompleted: false }) );
  setTodos(newTodos)
}

// 전체 todos의 check 여부 (isCompleted)
const checkIsAllCompleted = () => {
  if(getAllTodos().length === getCompletedTodos().length ){
      setIsAllCompleted(true);
      completeAllBtnElem.classList.add('checked');
  }else {
      setIsAllCompleted(false);
      completeAllBtnElem.classList.remove('checked');
  }
}

const onClickCompleteAll = () => {
  if(!getAllTodos().length) return; // todos배열의 길이가 0이면 return;

  if(isAllCompleted) incompleteAll(); // isAllCompleted가 true이면 todos를 전체 미완료 처리 
  else completeAll(); // isAllCompleted가 false이면 todos를 전체 완료 처리 
  setIsAllCompleted(!isAllCompleted); // isAllCompleted 토글
  paintTodos(); // 새로운 todos를 렌더링
  setLeftItems()
}

const appendTodos = (text) => {
  const newId = id_todo + 1; // 기존에 i++ 로 작성했던 부분을 setId()를 통해 id값을 갱신하였다.
  setId(newId)
  const newTodos = getAllTodos().concat({id: newId, isCompleted: false, content: text })
  // const newTodos = [...getAllTodos(), {id: newId, isCompleted: false, content: text }]
  setTodos(newTodos)
  setLeftItems()
  checkIsAllCompleted();
  paintTodos();
}

const deleteTodo = (todoId) => {
  const newTodos = getAllTodos().filter(todo => todo.id !== todoId );
  setTodos(newTodos);
  setLeftItems()
  paintTodos()
}

const completeTodo = (todoId) => {
  const newTodos = getAllTodos().map(todo => todo.id === todoId ? {...todo,  isCompleted: !todo.isCompleted} : todo )
  setTodos(newTodos);
  paintTodos();
  setLeftItems()
  checkIsAllCompleted();
}

const updateTodo = (text, todoId) => {
  const currentTodos = getAllTodos();
  const newTodos = currentTodos.map(todo => todo.id === todoId ? ({...todo, content: text}) : todo);
  setTodos(newTodos);
  paintTodos();
}

const onDbclickTodo = (e, todoId) => {
  const todoElem = e.target;
  const inputText = e.target.innerText;
  const todoItemElem = todoElem.parentNode;
  const inputElem = document.createElement('input');
  inputElem.value = inputText;
  inputElem.classList.add('edit-input');
  inputElem.addEventListener('keypress', (e)=>{
      if(e.key === 'Enter') {
          updateTodo(e.target.value, todoId);
          document.body.removeEventListener('click', onClickBody );
      }
  })

  const onClickBody = (e) => {
      if(e.target !== inputElem)  {
          todoItemElem.removeChild(inputElem);
          document.body.removeEventListener('click', onClickBody );
      }
    }
    
    document.body.addEventListener('click', onClickBody)
    todoItemElem.appendChild(inputElem);
}

const clearCompletedTodos = () => {
    const newTodos = getActiveTodos()
    setTodos(newTodos)
    paintTodos();
}

const paintTodo = (todo) => {
    const todoItemElem = document.createElement('li');
    todoItemElem.classList.add('todo-item');

    todoItemElem.setAttribute('data-id', todo.id );

    const checkboxElem = document.createElement('div');
    checkboxElem.classList.add('checkbox');
    checkboxElem.addEventListener('click', () => completeTodo(todo.id))

    const todoElem = document.createElement('div');
    todoElem.classList.add('todo');
    todoElem.addEventListener('dblclick', (event) => onDbclickTodo(event, todo.id))
    todoElem.innerText = todo.content;

    const delBtnElem = document.createElement('button');
    delBtnElem.classList.add('delBtn');
    delBtnElem.addEventListener('click', () =>  deleteTodo(todo.id))
    delBtnElem.innerHTML = 'X';

    if(todo.isCompleted) {
        todoItemElem.classList.add('checked');
        checkboxElem.innerText = '✔';
      }
  
      todoItemElem.appendChild(checkboxElem);
      todoItemElem.appendChild(todoElem);
      todoItemElem.appendChild(delBtnElem);
  
      todoListElem.appendChild(todoItemElem);
  }
  
  const paintTodos = () => {
      todoListElem.innerHTML = null;
  
      switch (currentShowType) {
          case 'all':
              const allTodos = getAllTodos();
              allTodos.forEach(todo => { paintTodo(todo);});
              break;
          case 'active': 
              const activeTodos = getActiveTodos();
              activeTodos.forEach(todo => { paintTodo(todo);});
              break;
          case 'completed': 
              const completedTodos = getCompletedTodos();
              completedTodos.forEach(todo => { paintTodo(todo);});
              break;
          default:
              break;
      }
  }
  
  const onClickShowTodosType = (e) => {
      const currentBtnElem = e.target;
      const newShowType = currentBtnElem.dataset.type;
      if ( currentShowType === newShowType ) return;

    const preBtnElem = document.querySelector(`.show-${currentShowType}-btn`);
    preBtnElem.classList.remove('selected');

    currentBtnElem.classList.add('selected')
    setCurrentShowType(newShowType)
    paintTodos();
}

const initTodo = () => {
  todoInputElem.addEventListener('keypress', (e) =>{
    if( e.key === 'Enter' ){
        appendTodos(e.target.value); todoInputElem.value ='';
    }
  });

    completeAllBtnElem.addEventListener('click',  onClickCompleteAll);
    showAllBtnElem.addEventListener('click', onClickShowTodosType);
    showActiveBtnElem.addEventListener('click',onClickShowTodosType);
    showCompletedBtnElem.addEventListener('click',onClickShowTodosType);
    clearCompletedBtnElem.addEventListener('click', clearCompletedTodos);
    setLeftItems()
}

initTodo();
initReport();