<#assign liferay_portlet = taglibLiferayHash["/WEB-INF/tld/liferay-portlet.tld"] />

<h1>Zprávy</h1>
<#if entries?has_content>
<table>
    <#list entries as curEntity>
        <tr>
            <td>
                <h3>${curEntity.subject}</h3>
                <p>${curEntity.body}</p>
            </td>
        </tr>
    </#list>
</table>
</#if>