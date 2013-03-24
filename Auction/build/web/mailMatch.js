/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
        $("#signUp").click(function(){
            var first = $("#mail").val();
            var second = $("#re_mail").val();
            var error=false;
            if (first != second){
                $("div.hero-unit").before('<div class="alert alert-error"><strong>mails do not match!</strong></div>');
                $("div.alert").fadeOut(4000);
                error=true;
            }
            else if(first == ""){
                $("div.hero-unit").before('<div class="alert alert-error" ><strong>insert a mail address</strong><div>');
                $("div.alert").fadeOut(4000);
                error=true;
            }
            else if (second == "") {
                $("div.hero-unit").before('<div class="alert alert-error" ><strong>insert a mail address</strong><div>');
                $("div.alert").fadeOut(4000);
                error=true;
            }
            if (error){return false}
           
        });
});
