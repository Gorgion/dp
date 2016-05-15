/** Global namespace definition */
window.DP = window.DP || {};
/** Common functions. These function are not bound to any portlet. */
jQuery(function () {

    /** Replace button hrefs by buttons */
    jQuery("a.js-on-aToButton").each(function () {
        var a = jQuery(this);
        var button = jQuery("<button />")
            .text(a.text())
            .click(function () {
                window.location = a.attr("href");
            });
        a.replaceWith(button);
    });

    /** Adds window location change to button */
    jQuery("button.js-on-goToPage").on('click', function (event) {
        var button = jQuery(this);
        window.location = button.data('url');
        event.preventDefault();
    });

    /** Adds window location change to element with/without confirm  */
    jQuery(".js-on-actionUrlButton").on('click', function (event) {
        var button = jQuery(this);
        var url = button.data('url');
        var confirmText = button.data('url-confirm');
        if (url) {
            if (!confirmText || confirm(confirmText)) {
                if (url.indexOf("javascript:") == 0) {
                    eval(url.substr("javascript:".length));
                } else {
                    window.location = url;
                }
            }
            event.preventDefault();
        }
        return false;
    });
})