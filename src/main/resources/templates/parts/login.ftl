<#include "security.ftl">
<#macro login path isRegisterForm>
<form action="${path}" method="post">
  <div class="form-group row">
    <label class="col-sm-2 col-form-label"> Login: </label>
    <div class="col-sm-6">
      <input type="text" name="username" class="form-control ${(userNameError??)?string('is-invalid', '')}"
             value="<#if user??>${user.username}</#if>"
             placeholder="Username"/>
      <#if userNameError??>
          <div class="invalid-feedback">
            ${userNameError}
          </div>
      </#if>
    </div>
  </div>
  <#if isRegisterForm>
    <div class="form-group row">
      <label class="col-sm-2 col-form-label"> User Name: </label>
      <div class="col-sm-6">
        <input type="text" name="name" class="form-control ${(nameError??)?string('is-invalid', '')}"
               value="<#if user??>${user.name}</#if>"
               placeholder="name"/>
        <#if nameError??>
            <div class="invalid-feedback">
              ${nameError}
            </div>
        </#if>
      </div>
    </div>
  </#if>
  <div class="form-group row">
    <label class="col-sm-2 col-form-label"> Password: </label>
    <div class="col-sm-6">
      <input type="password" name="password" class="form-control ${(passwordError??)?string('is-invalid', '')}"
             placeholder="Password"/>
       <#if passwordError??>
          <div class="invalid-feedback">
            ${passwordError}
          </div>
       </#if>
    </div>
  </div>
  <#if isRegisterForm>
    <div class="form-group row">
      <label class="col-sm-2 col-form-label"> Password: </label>
      <div class="col-sm-6">
        <input type="password" name="password2" class="form-control ${(password2Error??)?string('is-invalid', '')}"
               placeholder="Retype password"/>
         <#if password2Error??>
            <div class="invalid-feedback">
              ${password2Error}
            </div>
         </#if>
      </div>
    </div>
    <div class="form-group row">
      <label class="col-sm-2 col-form-label"> Email: </label>
      <div class="col-sm-6">
        <input type="email" name="email" class="form-control ${(emailError??)?string('is-invalid', '')}"
               value="<#if user??>${user.email}</#if>"
               placeholder="some@some.com"/>
        <#if emailError??>
            <div class="invalid-feedback">
              ${emailError}
            </div>
        </#if>
      </div>
    </div>
  </#if>
  <input type="hidden" name="_csrf" value="${_csrf.token}" />
  <#if !isRegisterForm>
    <a href="/registration">Registration</a>
  </#if>
  <button class="btn btn-primary" type="submit"><#if isRegisterForm>Registration<#else>Sign In</#if></button>
</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
  <input type="hidden" name="_csrf" value="${_csrf.token}" />
  <#if user??><button class="btn btn-primary" type="submit">Sign Out</button></#if>
</form>
</#macro>