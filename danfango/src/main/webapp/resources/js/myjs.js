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
        $.ajax({
                url : '/danfango/changeFavorite', // Your Servlet mapping or JSP(not suggested)
                //data :'movieid' + movieid, 
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
    $('.slider2').bxSlider({
        slideWidth: 2000,
        minSlides: 1,
        maxSlides: 10,
        slideMargin: 1000
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
            // change the class on the button to 'seat-unselected' from 'seat-selected'
            // after we've done this we want to remove this button from the array
            // so that we have an accurate record of the users selected seats
            // that we can send to the backend
            // will have to get id off of the button 
            // will have to loop through our array of selected buttons and remove the button with this id
            // ^ should probably call a method to do this so this function doesn't get too long 
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


function checkFavorite(movieid)
{
    
    $.ajax({
                url : 'danfango/getFavorite', // Your Servlet mapping or JSP(not suggested)
                data :movieid, 
                type : 'POST',
                dataType : 'text', // Returns HTML as plain text; included script tags are evaluated when inserted in the DOM.
                success : function(response) {
                    $(document.getElementById('favorite'))[0].addClass('favoriteState')
                },
                error : function(request, textStatus, errorThrown) {
                    alert(errorThrown);
                }
            });
}