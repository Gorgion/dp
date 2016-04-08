/** Inicializacny skript pre form.jsp */
(function () {
    /** Constants */
    var WRAPPER = 'helloEditForm';
    var REQUIRED = "required";

    /** Default options. */
    var defaultOpts = {
        ns: "",
        localization: {
            name: "Please enter your name",
            email: "Please enter a valid email address"
        }
    }

    /** Privatna metoda zacina podtrzitkom.*/
    function _dialogInit(){
        /*predstavte si, ze je tu inicializacia dialogu :) */
    }


    /** Inicializacna funkcia. Jedna sa o jedinu public funkciu komponenty. Zabezpecuje celu inicializaciu.
    Sluzi na inicializaciu komponent a zakladnu funkcionalitu. Pre citatelnost moze byt rozbita do privatnych funkcii. */
    IBA.helloForm = function (options) {

        /** Mixing */
        var opt = $.extend({}, defaultOpts, options);

        /** Inicializacia komponenty*/
        $("#" + opt.ns + WRAPPER + " form").validate({
            rules: {
                name: REQUIRED,
                email: {
                    required: true,
                    email: true
                }
            },
            messages: {
                name: opt.localization.name,
                email: opt.localization.email
            }
        });

        /** volanie privatnej funkcie */
        _dialogInit();
    }
})();