/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {
    $('.fa-heart').on('click', function () {
        if ($(this).hasClass('favoriteState')) {
            $(this).removeClass('favoriteState');
            $.notify("Removed From Favorites")
        } else {
            $(this).addClass('favoriteState');
            $.notify("Added To Favorites")

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
