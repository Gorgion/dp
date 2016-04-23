function filterNone() {
    return NodeFilter.FILTER_ACCEPT;
}

function getAllComments(rootElem) {
    var comments = [];
    // Fourth argument, which is actually obsolete according to the DOM4 standard, is required in IE 11
    var iterator = document.createNodeIterator(rootElem, NodeFilter.SHOW_COMMENT, filterNone, false);
    var curNode;
    while (curNode = iterator.nextNode()) {
        comments.push(curNode.nodeValue);
    }
    return comments;
}


function getDuration(portlet) {
    var comments = getAllComments(document.body);

    // comments.forEach(function () {
    for (var i = 0; i < comments.length; i++) {

        var c = comments[i];
        var idx = c.indexOf(portlet);
        if (idx >= 0) {
            var idx2 = c.indexOf("duration=", idx);
            var duration = c.substring(idx2 + "duration=".length, c.indexOf(",", idx2));
            console.log(duration);
            return parseInt(duration);
        }
    };
}


AUI().ready('node', function () {
    var round = parseInt(localStorage.getItem("round"));
    var array = JSON.parse(localStorage.getItem("array"));
    if(isNaN(round)) {
        round = 0;
    }
    if (array == null) {
        array = [];
    }
    if(round < 10) {
        array.push(getDuration("MessageListingPortlet_WAR_dpportletapplication"));
        localStorage.setItem("array", JSON.stringify(array));
        localStorage.setItem("round", ++round);

        setTimeout(function () {
            window.location.reload();
        }, 3000);
    }
});