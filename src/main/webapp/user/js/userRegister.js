$(function () {

    $(".imgUpload").on("change", function (e) {

        $(".imgPreviewPlaceholder").attr("hidden", true);

        // 跑每個使用者選的檔案
        let reader = new FileReader(); // 用來讀取檔案

        reader.readAsDataURL(this.files[0]); // 讀取檔案
        reader.addEventListener("load", function (e) {
            $(".imgUpload").attr("src", reader.result);
        });
    });
});