document.getElementById('potionsList').addEventListener('click', function() {
    var dropdownList = document.getElementById('dropdownList');
    if (dropdownList.style.display === 'none') {
        dropdownList.style.display = 'block';
    } else {
        dropdownList.style.display = 'none';
    }
});