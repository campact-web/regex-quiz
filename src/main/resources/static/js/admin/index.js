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
$('#editQuizModal').on('show.bs.modal', function(event) {
	let button = $(event.relatedTarget); //モーダルを呼び出すときに使われたボタンを取得
	let selectedquiz = button.data('selectedquiz'); //data-whatever の値を文字列で取得
	//  console.log(selectedquiz);
	//  console.log(JSON.stringify(selectedquiz));
	//  let array = selectedquiz.split(",");

	let modal = $(this); //モーダルを取得
	console.log(selectedquiz);
	let quiz = JSON.stringify(selectedquiz);
	console.log(quiz);
	//JSON形式にする
	quiz = quiz.replace("(", "={");
	quiz = quiz.replace(")", "}");
	quiz = quiz.replace('=', '="');

	console.log(quiz);
	let hoge = JSON.stringify(quiz);
	console.log(hoge.quiz);
	let array = selectedquiz.split(",");
	for (let i = 0; i < array.length; i++) {
		console.log(array[i]);
	}

 	//モーダルのタイトルに値を表示
 	let quizId = array[0].replace("Quiz(id=", "");
 	modal.find('.modal-title').text('クイズNo. ' + quizId);
//	modal.find('.modal-title').text('クイズNo. ' + array[0].replace("Quiz(id=", ""));
	modal.find('.modal-body input#modal-id').val(quizId);
	//array[1]:問題文,  quiz=
	modal.find('.modal-body textarea#modal-quiz').val(array[1].replace("quiz=", ""));
	//array[2]:解答, answer1=
	modal.find('.modal-body input#modal-answer1').val(array[2].replace("answer1=", ""));
	//array[3]:その他選択肢, answer2=
	modal.find('.modal-body input#modal-answer2').val(array[3].replace("answer2=", ""));
	//array[4]:その他選択肢, answer3=
	modal.find('.modal-body input#modal-answer3').val(array[4].replace("answer3=", ""));
	//array[6]:その他選択肢, created_at=
	modal.find('.modal-body input#modal-createdAt').val(array[6].replace("created_at=", ""));
	//array[8]:その他選択肢, updated_at=
	modal.find('.modal-body input#modal-updatedAt').val(array[8].replace("updated_at=", ""));
})