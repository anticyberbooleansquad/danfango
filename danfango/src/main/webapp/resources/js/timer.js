/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function startTimer(duration, display) {
    var timer = duration, minutes, seconds;
    setInterval(function () {
        minutes = parseInt(timer / 60, 10)
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        display.textContent = minutes + ":" + seconds;

        if (--timer < 0) {
            timer = duration;
        }
    }, 1000);
}

window.onload = function () {
    var fiveMinutes = 60 * 5,
        display = document.querySelector('#time');
    startTimer(fiveMinutes, display);
    setTimeout(endTimer, (fiveMinutes * 1000) + 1000)
};

function endTimer()
{
    $.ajax({
                url : 'http://localhost:8080/danfango/unlockSeats', // Your Servlet mapping or JSP(not suggested)
                //data: {seatNumbers: userSelectedSeats},
                type : 'POST',
                success : function(response) {
                    window.location.replace('http://localhost:8080/danfango/index');
                },
                error : function(request, textStatus, errorThrown) {
                    alert(errorThrown);
                }
        });
}