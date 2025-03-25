// 아이디 중복 검사
document.getElementById('memId').addEventListener('keyup', function() {
	var memId = this.value;
	if (memId.length > 0) {
		fetch(`/checkMemId?memId=${memId}`)
			.then(response => response.json())
			.then(data => {
				const idError = document.getElementById('idError');
				if (data.exists) {
					idError.textContent = "이미 존재하는 아이디입니다.";
				} else {
					idError.textContent = "";
				}
			});
	}
});

// 닉네임 중복 검사
document.getElementById('memNickname').addEventListener('keyup', function() {
	var memNickname = this.value;
	if (memNickname.length > 0) {
		fetch(`/checkMemNickname?memNickname=${memNickname}`)
			.then(response => response.json())
			.then(data => {
				const nicknameError = document.getElementById('nicknameError');
				if (data.exists) {
					nicknameError.textContent = "이미 존재하는 닉네임입니다.";
				} else {
					nicknameError.textContent = "";
				}
			});
	}
});