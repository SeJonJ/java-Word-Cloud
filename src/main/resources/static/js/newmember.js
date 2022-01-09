var REGEX = {
    email : /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
    strongPassowrd: /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/,
    middlePassword : /^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,})/,
    cellPhone: /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/
};
/**stroung : 최소 하나의 대문자랑 소문자를 포함 시키고 숫자 하나를 포함 시킨뒤 특수문자를 포함시켜라 그리고 최소 8글자 이상 입력해라*/
/**middle : 최소 하나의 대문자나 소문자를 포함하거나, 소문자나 숫자하나를 포함하거나, 최소 대문자나 숫자를 포함하도록 최소 6글자이상*/
$(document).ready( function() {
    configurationListeners();
});
/*document에서 ready가 되면 funcgion을 실행해라 function의 내용은 configurationListners*/
function configurationListeners() {
    $("#userpw").keyup( function() {
        /**userpw를 누를때 */
        var input = $("#userpw");
        var value = input.val();

        input.attr("class", "");

        if( testRegex( REGEX.strongPassowrd, value ) ) {
            input.addClass("green-border");
        } else if( testRegex( REGEX.middlePassword, value ) ) {
            input.addClass("yellow-border");
        } else {
            input.addClass("red-border");
        }
    });
    $("#userpw").keyup( function() {
        /**userpw를 누를때 */
        var input = $("#userpw");
        var value = input.val();
        var pwd1 = $("#userpw").val();

        if( testRegex( REGEX.strongPassowrd, value ) ) {
            $("#alert-strong").css('display', 'inline-block');
            $("#alert-middle").css('display', 'none');
            $("#alert-weakness").css('display', 'none');
        } 
        else if( testRegex( REGEX.middlePassword, value ) ) {
            $("#alert-strong").css('display', 'none');
            $("#alert-middle").css('display', 'inline-block');
            $("#alert-weakness").css('display', 'none');
        }
        
        else if (pwd1 == ''){
            $("#alert-strong").css('display', 'none');
            $("#alert-middle").css('display', 'none');
            $("#alert-weakness").css('display', 'none');
        } 
        else {
            $("#alert-strong").css('display', 'none');
            $("#alert-middle").css('display', 'none');
            $("#alert-weakness").css('display', 'inline-block');
        }
    });
    $('.pw').keyup(function () {
        var pwd1 = $("#userpw").val();
        var pwd2 = $("#userpw2").val();
 
        if ( pwd1 != '' && pwd2 == '' ) {
            $("#alert-success").css('display', 'none');
            $("#alert-danger").css('display', 'none');
            $("#alert-null").css('display', 'inline-block');
            $("#alert-null2").css('display', 'none');
        }
        else if (pwd1 == '' && pwd2 ==''){
            $("#alert-success").css('display', 'none');
            $("#alert-danger").css('display', 'none');
            $("#alert-null").css('display', 'none');
            $("#alert-null2").css('display', 'none');
        }
        else if (pwd1 =='' && pwd2 !=''){
            $("#alert-success").css('display', 'none');
            $("#alert-danger").css('display', 'none');
            $("#alert-null").css('display', 'none');
            $("#alert-null2").css('display', 'inline-block');
        }
        else if (pwd1 != "" || pwd2 != "") {
            if (pwd1 == pwd2) {
                $("#alert-success").css('display', 'inline-block');
                $("#alert-danger").css('display', 'none');
                $("#alert-null").css('display', 'none');
                $("#alert-null2").css('display', 'none');
            }
            else {
                $("#alert-success").css('display', 'none');
                $("#alert-danger").css('display', 'inline-block');
                $("#alert-null").css('display', 'none');
                $("#alert-null2").css('display', 'none');
            }
        }
    });
    // $('.pw').focusout(function () {
    //     var pwd1 = $("#password_1").val();
    //     var pwd2 = $("#password_2").val();
 
    //     if ( pwd1 != '' && pwd2 == '' ) {
    //         null;
    //     } else if (pwd1 != "" || pwd2 != "") {
    //         if (pwd1 == pwd2) {
    //             $("#alert-success").css('display', 'inline-block');
    //             $("#alert-danger").css('display', 'none');
    //         } else {
    //             alert("비밀번호가 일치하지 않습니다. 비밀번호를 재확인해주세요.");
    //             $("#alert-success").css('display', 'none');
    //             $("#alert-danger").css('display', 'inline-block');
    //         }
    //     }
    // });
    $("#submit").click( function() 
    {
        var email = $("#notSelected").val().trim();
        var email = $("#etc").val().trim();
        
        if( email.length == 0 ) {
            alert("이메일을 입력해라");
            $("#notSelected").focus();
            return;
        }
        if( email.length == 0 ) {
            alert("이메일을 입력해라");
            $("#etc").focus();
            return;
        }

        alert("회원가입이 되었습니다.");
    });
}

/**
 * 정규식을 검사해주는 메서드
 * @param {RegExp} regex 
 * @param {String} str 
 */
function testRegex( regex, str ) {
    return regex.test( str );
}
// switch(emailValue){
//     case 'notSelected':
//         emailTail.readOnly = true;
//         emailTail.value = '';
//     break;

//     case 'etc' :
//         emailTail.readOnly = false;
//         emailTail.value = null;
//         emailTail.focus();
//         break;
//     default :
//     emailTail.readOnly = true;
//     emailTail.value = emailValue;
// }