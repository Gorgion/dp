/**
 * Created by Tomas on 23.04.2016.
 */
function filterNone() {
    return NodeFilter.FILTER_ACCEPT;
}

function getAllComments(rootElem) {
    var comments = [];
    var iterator = document.createNodeIterator(rootElem, NodeFilter.SHOW_COMMENT, filterNone, false);
    var curNode;
    while (curNode = iterator.nextNode()) {
        comments.push(curNode.nodeValue);
    }
    return comments;
}


function getDuration(portlet) {
    var comments = getAllComments(document.body);

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