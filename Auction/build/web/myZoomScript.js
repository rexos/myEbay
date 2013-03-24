/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    $("img").hover(function() {
        //$(this).css("z-index", 1);
        $(this).animate({
            height: "250",
            width: "250"
        }, "fast");
    }, function() {
       // $(this).css("z-index", 0);
        $(this).animate({
            height: "120",
            width: "120"
        }, "fast");
    });
});

