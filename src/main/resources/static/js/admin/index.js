$('#checkbox_all').on('click', function() {
  if (this.checked) {
    // 全ての個別チェックボックスを選択状態に
     $('input[name=checkbox_list]').prop('checked', true);
  } else {
    // 全ての個別チェックボックスを選択解除
    $('input[name=checkbox_list]').prop('checked', false);
  }
});
//モーダル
$('#editQuizModal').on('show.bs.modal', function (event) {
  let button = $(event.relatedTarget); //モーダルを呼び出すときに使われたボタンを取得
  let recipient = button.data('whatever'); //data-whatever の値を取得
  let array = recipient.split(",");

  //Ajaxの処理はここに
  var modal = $(this); //モーダルを取得
    for(let i = 0; i < array.length; i ++) {
	console.log(array[i]);
  }
  //array[1]:問題文,  quiz=
  //array[2]:解答, answer1=
  //array[3]:その他選択肢, answer2=
  //array[4]:その他選択肢, answer3=
  //array[5]:その他選択肢, registed_by=
  //array[6]:その他選択肢, created_at=
  //array[7]:その他選択肢,  updated_by=
  //array[8]:その他選択肢, updated_at=
  //array[9]:その他選択肢
  modal.find('.modal-title').text('クイズNo. ' + array[0].replace("Quiz(id=", "")); //モーダルのタイトルに値を表示
  //inputタグ表示
  modal.find('.modal-body textarea#modal-quiz').val(array[1].replace("quiz=", ""));  
  modal.find('.modal-body input#modal-answer1').val(array[2].replace("answer1=", "")); 
  modal.find('.modal-body input#modal-answer2').val(array[3].replace("answer2=", ""));
  modal.find('.modal-body input#modal-answer3').val(array[4].replace("answer3=", "")); 
  modal.find('.modal-body input#modal-createdAt').val(array[6].replace("created_at=", ""));
  modal.find('.modal-body input#modal-updatedAt').val(array[8].replace("updated_at=", ""));
})