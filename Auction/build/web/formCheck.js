/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    
    $("#submit").click(function(){
        var error=false;
        var product = $("#product").val();
        var quantity = $("#quant").val();
        var category = $("#category").val();
        var start_price = $("#start").val();
        var min = $("#min").val();
        var increase = $("#increase").val();
        var send = $("#send").val();
        var stop = $("#stop").val();
        var hour = $("#hour").val();
        if(product.trim() =='' || category.trim()=='' || quantity.trim() =='' || start_price.trim()=='' || increase.trim() =='' || send.trim()=='' || stop.trim()=='' || hour.trim()=='' || min.trim()==''){
            error = true;
        }
        if(error){
            $('html, body').animate({
                scrollTop:0
            }, 'slow');
            $("div.hero-unit").before('<div class="alert alert-error"><center><h3><strong>Some required fields are empty!</strong></h3></center></div>');
            $("div.alert").fadeOut(4000);
            return false;
        }
    });
});

