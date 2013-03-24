/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
    $(window).scroll( function(){
        if($(window).scrollTop()>200){
            $("#top").fadeIn('slow');
        } else {
            $("#top").fadeOut('slow');
        }
        
    });
    
    $('#top').click(function(){
            $('html, body').animate({scrollTop:0}, 'slow');
            return false;
        });
    
});


/*$(document).ready(function() {
	    
    $('#top').click(function(){
        $('html, body').animate({
            scrollTop:0
        }, 'slow');
        return false;
    });
	 
});
*/