<#import "parts/common.ftl" as c>
<@c.page>

<div class="card-columns" id="contacts">
    <#list contacts as contact>
      <div class="card my-3" data-id="${contact.id?c}">
        <div class="m-2">
          <span>${contact.name}</span><br/>
          <i>#${contact.surname}</i>
        </div>
        <div class="card-footer text-muted container">
          <div class="row">
            ${contact.id}
          </div>
        </div>
      </div>
    <#else>
        No contacts
    </#list>
</div>
</@c.page>