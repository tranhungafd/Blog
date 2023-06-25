const menubarBtn = document.querySelector('.header-top i')
menubarBtn.addEventListener("click",function(){
    document.querySelector('.header-top ul').classList.toggle('active')
})
//----------------------------------//
// Login
$("#login-button").click(function(event){
     event.preventDefault();

   $('form').fadeOut(500);
   $('.wrapper').addClass('form-success');
});