$(document).ready(function() {
    // 알러지 항목을 클릭하면 선택 목록에 추가
    $(document).on('click', '.allergy-item', function() {
        const allergyId = $(this).data('id');
        const allergyName = $(this).text();  // 알러지 이름

        // 이미 선택된 알러지가 아닐 경우에만 추가
        if ($('#selected-allergies .allergy-btn[data-id="' + allergyId + '"]').length === 0) {
            $('#selected-allergies').append('<button type="button" class="allergy-btn" data-id="' + allergyId + '">' +
                allergyName + ' <span class="remove-btn">X</span></button>');
        }
    });

    // 선택된 알러지 항목에서 'X' 버튼 클릭 시 제거
    $(document).on('click', '.remove-btn', function() {
        $(this).parent().remove();
    });

    // 폼 제출 시 선택된 알러지 정보도 함께 전송
    $('form').on('submit', function() {
        const selectedAllergies = [];
        $('#selected-allergies .allergy-btn').each(function() {
            selectedAllergies.push($(this).data('id'));
        });
        // 숨겨진 필드로 선택된 알러지 ID들 전송
        $('<input>').attr({
            type: 'hidden',
            name: 'selectedAllergies',
            value: selectedAllergies.join(',')
        }).appendTo('form');
    });
});
