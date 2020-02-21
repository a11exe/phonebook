<#import "parts/common.ftl" as c>
<@c.page>

<div class="container">
  <div class="col sm-6 mb-3">
    <form method="post" action="/find">
      <div class="input-group">

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="text" class="form-control"
               name="search"
               value="<#if search??>${(search)!}</#if>"
               placeholder="Search contact">
        <div class="input-group-append">
          <button class="btn btn-secondary" type="submit">
            <i class="fa fa-search"></i>
          </button>
        </div>

      </div>
    </form>
  </div>
</div>

<div class="container">
  <div class="col sm-6 mb-3">
    <a class="btn btn-primary mb-3" data-toggle="collapse" href="#collapseExample" role="button"
       aria-expanded="false" aria-controls="collapseExample">
  <#if contact??>Edit contact<#else >Add contact</#if>
    </a>
  </div>
</div>

<div class="collapse <#if contact??>show</#if>" id="collapseExample">
  <div class="form-group mt-3">
    <form method="post">
      <div class="form-group">
        <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}"
               value="<#if contact??>${(contact.name)!}</#if>" name="name" placeholder="Введите Имя" />
        <#if nameError??>
          <div class="invalid-feedback">
            ${nameError}
          </div>
        </#if>
      </div>
      <div class="form-group">
        <input type="text" class="form-control ${(surnameError??)?string('is-invalid', '')}"
               value="<#if contact??>${(contact.surname)!}</#if>" name="surname" placeholder="Введите Фамилию" />
        <#if surnameError??>
          <div class="invalid-feedback">
            ${surnameError}
          </div>
        </#if>
      </div>
      <div class="form-group">
        <input type="text" class="form-control ${(middleNameError??)?string('is-invalid', '')}"
               value="<#if contact??>${(contact.middleName)!}</#if>" name="middleName" placeholder="Введите Отчество" />
        <#if middleNameError??>
          <div class="invalid-feedback">
            ${middleNameError}
          </div>
        </#if>
      </div>
      <div class="form-group">
        <input type="text" class="form-control ${(phoneMobileError??)?string('is-invalid', '')}"
               value="<#if contact??>${(contact.phoneMobile)!}</#if>" name="phoneMobile" placeholder="Введите мобильный телефон" />
        <#if phoneMobileError??>
          <div class="invalid-feedback">
            ${phoneMobileError}
          </div>
        </#if>
      </div>

      <div class="form-group">
        <input type="text" class="form-control ${(phoneHomeError??)?string('is-invalid', '')}"
               value="<#if contact??>${(contact.phoneHome)!}</#if>" name="phoneHome" placeholder="Введите домашний телефон" />
        <#if phoneHomeError??>
          <div class="invalid-feedback">
            ${phoneHomeError}
          </div>
        </#if>
      </div>

      <div class="form-group">
        <input type="text" class="form-control ${(addressError??)?string('is-invalid', '')}"
               value="<#if contact??>${(contact.address)!}</#if>" name="address" placeholder="Введите адрес" />
        <#if addressError??>
          <div class="invalid-feedback">
            ${addressError}
          </div>
        </#if>
      </div>
      <div class="form-group">
        <input type="text" class="form-control ${(emailError??)?string('is-invalid', '')}"
               value="<#if contact??>${(contact.email)!}</#if>" name="email" placeholder="Введите email" />
        <#if emailError??>
          <div class="invalid-feedback">
            ${emailError}
          </div>
        </#if>
      </div>

      <input type="hidden" name="_csrf" value="${_csrf.token}" />
      <input type="hidden" name="id" value=0 />
      <button class="btn btn-primary" type="submit">Save contact</button>
    </form>
  </div>
</div>

<div class="container">
  <div class="col sm-6">
    <table class="table">
      <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">Name</th>
        <th scope="col">Surname</th>
        <th scope="col">Middle name</th>
        <th scope="col">Mobile phone</th>
        <th scope="col">Home phone</th>
        <th scope="col">Address</th>
        <th scope="col">Email</th>
        <th scope="col">Edit</th>
        <th scope="col">Delete</th>
      </tr>
      </thead>
      <tbody>
  <#list contacts as contact>
      <tr>
        <th scope="row">${contact?counter}</th>
        <td>${(contact.name)!}</td>
        <td>${(contact.surname)!}</td>
        <td>${(contact.middleName)!}</td>
        <td>${(contact.phoneMobile)!}</td>
        <td>${(contact.phoneHome)!}</td>
        <td>${(contact.address)!}</td>
        <td>${(contact.email)!}</td>
        <td><a class="btn btn-primary" href="/edit/${contact.id?c}" role="button"><i class="fas fa-user-edit"></i></a></td>
        <td><a class="btn btn-primary" href="/delete/${contact.id?c}" role="button"><i class="fas fa-user-times"></i></a></td>
      </tr>
  <#else>
        <th scope="row">1</th>
        <td>No contacts</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
  </#list>
      </tbody>
    </table>
  </div>
</div>

</@c.page>