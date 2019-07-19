$(document).ready(function(){
    //재로 사이드메뉴 열기/닫기
    var isopen=false;
    $('#side_btn').on('click',function(){
        if(isopen){
            $('#side_menu').animate({right:-700},800);
            $('#side_btn img').css({'transform':'rotate(180deg)'});
            $('#side_btn').animate({ 'margin-left':-100},1200);
            isopen=false;
        }
        else{
            $('#side_menu').animate({right:0},800);
            $('#side_btn img').css({'transform':'rotate(0)'});
             $('#side_btn').animate({ 'margin-left':0},300);
            isopen=true;
        }
    })

    //재로 갯수만큼 재료div 생성
    var ing_num=30;
    var ing_html="";    
    for(var i=0;i<ing_num;i++)
        ing_html+="<div class='ingredients' id='재료"+i+"'>"+i+"</div>"
    $('#ing_maker').html(ing_html);

    //드래그하는 부분
    var name="";
    $('.ingredients').on('mousedown',function(){
        name=$(this).prop('id');
    }).draggable({ 
        scroll: false,
        helper: "clone",
        revert: true,
        opacity: 0.90
    });

    //드롭하는부분
    var select_num=0;
    $('#main_contents').droppable({
        drop: function( event, ui ) {            
            select_num++;
            if(select_num<10){
                var selected =  $('#selected_ing_div').html()
                +" <div class='selected_ing'><button class='close_ing'></button><p>"
                +name+"</p></div>";
                $('#selected_ing_div').html(selected);
            }else{
                alert("재료는 10개까지만 선택 가능합니다.");
            }

            //드롭된 재료들 삭제
            $('.close_ing').on('click',function(){
                $(this).parents('.selected_ing').remove();
                select_num--;
            })            
        }
    });
})