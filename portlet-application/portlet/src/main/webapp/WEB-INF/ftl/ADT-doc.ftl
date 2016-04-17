<h1>Dokumenty</h1>
<#if entries?has_content>
    <#list entries as curEntity>
        <#assign detailURL = portletURLFactory.create(request,"DocumentListingPortlet_WAR_dpportletapplication", themeDisplay.getPlid() ,"RENDER_PHASE")>


            <h1><a href="${detailURL}">${curEntity.title}</a></h1>
            <p>${curEntity.description}</p>
    </#list>
</#if>