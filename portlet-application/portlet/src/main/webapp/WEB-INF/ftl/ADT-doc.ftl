<#assign liferay_portlet = taglibLiferayHash["/WEB-INF/tld/liferay-portlet.tld"] />
<#assign liferay_ui = taglibLiferayHash["/WEB-INF/tld/liferay-ui.tld"] />

<h1>Dokumenty</h1>
<#if entries?has_content>
<table>
    <#list entries as curEntity>
        <tr class="row-fluid">
            <@liferay_portlet.renderURL var="detailURL" windowState="normal">
                <@liferay_portlet.param name="page" value="detail" />
                <@liferay_portlet.param name="entityId" value="${curEntity.id}" />
            </@liferay_portlet.renderURL>
            <td class="span11">
                <h3><a href="${detailURL}">${curEntity.title}</a></h3>
                <p>${curEntity.description}</p>
            </td>
            <td>
                <span>
                <@liferay_ui.icon image="view" url="${detailURL}" message="Detail"/>
                </span>
                <@liferay_portlet.renderURL var="editUrl" windowState="normal">
                    <@liferay_portlet.param name="page" value="edit" />
                    <@liferay_portlet.param name="entityId" value="${curEntity.id}" />
                </@liferay_portlet.renderURL>
                <span>
                <@liferay_ui.icon image="edit" url="${editUrl}" message="Upravit"/>
                </span>
                <@liferay_portlet.actionURL name="deleteAction" var="deleteUrl">
                    <@liferay_portlet.param name="entityId" value="${curEntity.id}" />
                </@liferay_portlet.actionURL>

                <span>
                    <a class="taglib-icon" href="${deleteUrl}" onclick="return confirm('Opravdu si přejete smazat dokument?')">
                        <@liferay_ui.icon image="delete" message="Smazat"/>
                    </a>
                </span>
            </td>
        </tr>
    </#list>
</table>
</#if>