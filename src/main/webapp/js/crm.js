
$(document).ready(function(){
        $(".home").on({
            mouseenter: function(){
                $(this).css("text-decoration","underline");
            },
            mouseleave: function(){
                $(this).css("text-decoration","none");
            }
        });

});
