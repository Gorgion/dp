/** Inicializacny skript pre view.jsp */
(function () {
    /** Constants */
    var WRAPPER = 'helloView';

    /** Default options. */
    var defaultOpts = {
        ns: "",
        deleteUrl: "",
        localization: {
            deleteQuestion: 'Are you sure?'
        }
    }

    /** Inicializacna funkcia. Jedna sa o jedinu public funkciu komponenty. Zabezpecuje celu inicializaciu.
    Sluzi na inicializaciu komponent a zakladnu funkcionalitu. Pre citatelnost moze byt rozbita do privatnych funkcii. */
    IBA.helloView = function (options) {

        /** Mixing */
        var opts = $.extend({}, defaultOpts, options);

        /** Wrapper, ktory zastresuje cely markup. Sluzi ako root pre vyhladavanie. Pre zefektivnenie
         * vyhladavame pouzivame find na tomto objekte. */
        var wrapper = $("#" + opts.ns + WRAPPER );

        /** ukazka naviazania akcie na element oznaceny js-on- prefixom */
        /*wrapper.find("a.js-on-deleteItem").on('click',function(event){
            if(confirm(opts.localization.deleteQuestion)){
                window.location = opts.deleteUrl;
            }
            event.preventDefault();
            return false;
        });*/

    }
})();
