/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
    $("#bet").click(function(){
        var error = false;
        var Stringamount = $("#cash").val();
        
        var Stringstart = $("#start").text();
        var amount = parseInt(Stringamount);
        var start = parseInt(Stringstart);
        if (amount <= start){
            $("div.modal-footer").before('<div id="error" class="alert alert-error" style="width : 80%"><strong>Your offer is too small</strong></div>');
            $("#error").fadeOut(3000);
            error=true;
        }
        if (error) return false;
    });
});
