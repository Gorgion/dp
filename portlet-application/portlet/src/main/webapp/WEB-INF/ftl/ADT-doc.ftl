<#assign liferay_portlet = taglibLiferayHash["/WEB-INF/tld/liferay-portlet.tld"] />

<h1>Dokumenty</h1>
<#if entries?has_content>
<table>
    <#list entries as curEntity>
        <tr>
            <td>
                <@liferay_portlet.renderURL var="detailURL" windowState="normal">
                    <@liferay_portlet.param name="page" value="detail" />
                    <@liferay_portlet.param name="entityId" value="${curEntity.id}" />
                </@liferay_portlet.renderURL>

                <h3><a href="${detailURL}">${curEntity.title}</a></h3>
                <p>${curEntity.description}</p>
            </td>
        </tr>
    </#list>
</table>
</#if>