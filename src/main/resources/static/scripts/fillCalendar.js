window.onload = function() {
    let monthNumber = document.getElementById("months").value;
    populateDays(monthNumber);
    let dayNumber = document.getElementById("days").value;
    populateHours(monthNumber, dayNumber);
}

function populateDays(month) {
    let current = new Date();
    let currentMonth = current.getMonth() + 1;

    if (currentMonth == month) {
        let lastDate = new Date(current.getFullYear(), current.getMonth() + 1, 0).getDate();
        addDays(current.getDate(), lastDate);
    } else {
        let date = new Date(current.getFullYear(), month - 1, 1);
        let lastDate = new Date(date.getFullYear(), date.getMonth() + 1, 0).getDate();
        addDays(1, lastDate);
    }
}

function addDays(from, to) {
    let days = document.getElementById("days");
    days.innerHTML = "";
    for (let i=from; i<=to; ++i) {
        days.innerHTML += `<option value="${i}">${i}</option>`;
    }
}

function onMonthChange() {
    let monthNumber = document.getElementById("months").value;
    populateDays(monthNumber);
    let dayNumber = document.getElementById("days").value;
    populateHours(monthNumber, dayNumber);
}

function onDayChange() {
    let monthNumber = document.getElementById("months").value;
    let dayNumber = document.getElementById("days").value;
    populateHours(monthNumber, dayNumber);
}


function populateHours(month, day) {
    let current = new Date();
    let currentMonth = current.getMonth() + 1;
    let currentDay = current.getDate();

    if (currentMonth == month && currentDay == day) {
        addHours(current.getHours(), 18);
    } else {
        addHours(10, 17);
    }
}

function addHours(from, to) {
    let hours = document.getElementById("hours");
    hours.innerHTML = "";
    for (let i=from; i<=to; ++i) {
        hours.innerHTML += `<option value="${i}">${i}</option>`;
    }
}