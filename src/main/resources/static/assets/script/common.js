var swiperTarget = [
    {
        selector: '.gallery-box .swiper-container',
        options: {
            spaceBetween: 20,
            slidesPerView: 3,
            loop: true
        }
    }, {
        selector: '.update-box .swiper-container',
        options: {
            spaceBetween: 20,
            slidesPerView: 3,
            loop: true
        }
    }
];

var swiperList = [];

$(document).ready(function() {
    getGalleryData();
    configuration();
    initializeSwiper();
});

function getGalleryData() {
    $.ajax({
        url: "/data/gallery.json"
        , method: "GET"
        , dataType: "json"
        , success: function( data, textStatus, jqXHR ) {
            console.log( data );
        }, error : function( jqXHR, textStatus, errorThrown ) {
            alert("어서오세요.");
        }
    })
}

function configuration() {
    $(window).resize( function() {
        var slidesPerView = 0;

        if( $(window).width() <= 800 && $(window).width() > 600 ) {
            slidesPerView = 2;
        } else if( $(window).width() <= 600 ) {
            slidesPerView = 1;
        } else {
            slidesPerView = 3;
        }

        var i, n = swiperList.length;

        for( i = 0; i < n; i++ ){
            var item = swiperList[i];

            item.params.slidesPerView = slidesPerView;
            item.update();
        }

        // swiperList.forEach( function( item ) {
        //     item.params.slidesPerView = slidesPerView;
        //     item.update();
        // });
    });

    $(document).scroll( function() {
        var elements = $("#home nav a");
        var i, n = elements.length;

        for( i = 0; i < n; i++ ){
            var element = $( elements[i] );
            var selector = element.attr("href");
            var target = $( selector );

            var startPosition = target.offset().top;
            var boxHeight = parseInt( target.css("height").replace("px", "") );
            var endPosition = startPosition + boxHeight;
            var currentPosition = $(document).scrollTop();

            if( currentPosition >= startPosition && currentPosition < endPosition ) {
                $("li a[href='" + selector + "']").addClass("active");
            } else {
                $("li a[href='" + selector + "']").removeClass("active");
            }
        }
    })

    $("#btnMenu").click(function() {
        $(".mobile-nav").toggleClass("active");
    });
}

function initializeSwiper() {
    var i, n = swiperTarget.length;

    for( i = 0; i < n; i++ ){
        var item = swiperTarget[i];
        createSwiper( item.selector, item.options );
    }

    // swiperTarget.forEach( function( item ){
    //     createSwiper( item.selector, item.options );
    // });
}

function createSwiper( selector, options ) {
    var mySwiper = new Swiper (selector, options);
    swiperList.push( mySwiper );
}