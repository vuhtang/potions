document.getElementById("redirectStatusButton").addEventListener("click", function() {
    window.location.href = "http://localhost:8080/user/checkStatusPage";
});

document.getElementById("redirectOrderButton").addEventListener("click", function() {
    window.location.href = "https://example.com";
});

// const canvas = document.getElementById('levitatingCanvas1');
// const context = canvas.getContext('2d');
// const image = new Image();
//
// image.src = 'images/qwer1.png';
//
// image.onload = function() {
//     animate();
// };
//
// function animate() {
//     requestAnimationFrame(animate);
//     context.clearRect(0, 0, canvas.width, canvas.height);
//     context.drawImage(image, 150, Math.sin(Date.now() * 0.002) * 20 + 100, 200, 200);
// }
//
// const canvass = document.getElementById('levitatingCanvas2');
// const contextt = canvass.getContext('2d');
// const imagee = new Image();
//
// imagee.src = 'images/qwer2.png';
//
// imagee.onload = function() {
//     animate2();
// };
//
// function animate2() {
//     requestAnimationFrame(animate2);
//     contextt.clearRect(0, 0, canvass.width, canvass.height);
//     contextt.drawImage(imagee, 0, Math.sin(Date.now() * 0.002) * 20 + 100, 200, 200);
// }