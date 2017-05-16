/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var userSelectedSeats = [];


$(function () {
    $('.fa-heart').on('click', function () {
        if ($(this).hasClass('favoriteState')) {
            $(this).removeClass('favoriteState');
            $.notify("Removed From Favorites");
        } else {
            $(this).addClass('favoriteState');
            $.notify("Added To Favorites");

        }
    });
});

$(function () {
    $('.slider2').bxSlider({
        slideWidth: 2000,
        minSlides: 1,
        maxSlides: 10,
        slideMargin: 1000
    });
});

$(function() {
    $("#payment-button").click(function(){
        // userSelectedSeats
        // redirect using ajax to the payment controller where we will handle locking tickets
        // obviously add the selected seats to the ajax call 
        
        $.ajax({
                url : 'http://localhost:8080/danfango/paymentpage', // Your Servlet mapping or JSP(not suggested)
                data: {seatNumbers: userSelectedSeats},
                type : 'POST',
                success : function(response) {
                    alert("success");
                },
                error : function(request, textStatus, errorThrown) {
                    alert(errorThrown);
                }
        });
    });
});


$(function () {
    $(".seatButton").click(function () {
        var button = $(this);
        var classList = button.attr('class').split(/\s+/);
        var unselected = false;
        // loop over the classes to figure out if this was a previously unselected seat
        $.each(classList, function (index, className) {
            if (className === 'seat-unselected') {
                unselected = true;
            }
        });
        var buttonId = button.attr('id');
        if (unselected) {
            button.removeClass("seat-unselected");
            button.addClass("seat-selected");
            // change the class on the button to 'seat-selected' from 'seat-unselected'
            // how do we do this because button has multiple classes
            // CONVERT BUTTON ID TO STRING 
            userSelectedSeats.push(buttonId);
        } else {
            button.removeClass("seat-selected");
            button.addClass("seat-unselected");
            var index;
            for (var i = 0; i < userSelectedSeats.length; i++) {
                if (userSelectedSeats[i] === buttonId) {
                    index = i;
                }
            }
            userSelectedSeats.splice(index, 1);
        }
    });
});


$(function () {
    $(".changeSeats").click(function () {
        var length = userSelectedSeats.length;
        for (var i = 0; i < length; i++) {
            var id = userSelectedSeats[i];
            var button = $("#" + id);
            button.removeClass("seat-selected");
            button.addClass("seat-unselected");
        }
        userSelectedSeats = [];

    });
});

document.getElementById('timer').innerHTML = 05 + ":" + 00;
startTimer();

function startTimer() {
    var presentTime = document.getElementById('timer').innerHTML;
    var timeArray = presentTime.split(/[:]+/);
    var m = timeArray[0];
    var s = checkSecond((timeArray[1] - 1));
    if (s === 59) {
        m = m - 1;
    }
    //if(m<0){alert('timer completed')}

    document.getElementById('timer').innerHTML = m + ":" + s;
    setTimeout(startTimer, 1000);
    
    if(m === 0 && s === 0){
        document.getElementById('timer').innerHTML = "Done";
    }
}

function checkSecond(sec) {
    if (sec < 10 && sec >= 0) {
        sec = "0" + sec;
    }
    ; // add zero in front of numbers < 10
    if (sec < 0) {
        sec = "59";
    }
    ;
    return sec;
}