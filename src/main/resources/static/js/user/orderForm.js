// function submitCart() {
//     var selectedItems = cart.items;
//     var customerName = document.getElementById('userName').value;
//
//     var formData = new FormData();
//     formData.append('userName', customerName);
//     formData.append('selectedItems', JSON.stringify(selectedItems));
//
// //todo настроить маршрутизацию верную
//     fetch('/createOrder', {
//         method: 'POST',
//
//         body: formData
//     }).then(response => {
//         if (response.ok) {
// //todo добавить редирект на страницу о том что заказ создан
//             window.location.href = "http://localhost:8080/user/orderCreateComplete";
//         } else {
//             console.error('Ошибка запроса на создание ордера');
//         }
//     }).catch(error => {
//         console.error('Ошибка сети:', error);
//     });
// }