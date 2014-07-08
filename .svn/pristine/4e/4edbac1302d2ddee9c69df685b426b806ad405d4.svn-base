<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15"%>
<%@ include file="/WEB-INF/vues/include.jsp" %>
<%@ page isELIgnored="false" %>

<style>
.label1 {
float: left;	
	width: 80px;	
	text-align: left;	
	margin: 0;
	padding: 0 .5em 0 0;
	line-height: 1.8;
	font-weight: bold;
}
</style>

<fieldset>
<legend>Editions des PV</legend>
<div class="info">
La recherche peut s'effectuer :
<ul>
	<li>par diplôme, période, niveau ou UV</li>
<!-- 	
	<li>ou par nom</li>
	<li>ou par numéro</li>
 -->
</ul>
</div>


<form:form method="post" action="${command.url_action}">
	<form:hidden path="id" />
	<form:hidden path="action" />
	
 <form:label path="diplome" cssClass="label1">Diplôme</form:label>
 <html:select path="command.diplome" size="1"  onchange="javascript:changeFiltre(this)" >
<!--   <option value="">Tous</option>  --> 
 	<html:options items="${listeDiplomes}"		value="id" label="libelle"  selected="${command.diplome}" addEmptyOption="false"/>
 </html:select>
 

 <form:label path="periode" cssClass="label">Période</form:label>
 
 <html:select path="command.periode" size="1"  onchange="javascript:changeFiltre(this)" >
  <!--  <option value="">Toutes</option>  -->
 	<html:options items="${listePeriodes}"		value="id" label="libelle"  selected="${command.periode}" addEmptyOption="false"/>
 </html:select>  
 <form:errors path="periode" cssClass="erreur" />
 
 <form:label path="niveau" cssClass="label">Niveau</form:label>
 <html:select path="command.niveau" size="1"  onchange="javascript:changeFiltre(this)" >
  <option value="">Tous</option> 
 	<html:options items="${listeNiveaux}"		value="id" label="libelle"  selected="${command.niveau}" addEmptyOption="false"/>
 </html:select>   
 <form:errors path="niveau" cssClass="erreur" />

	<hr/>

<dl>
  <dt><form:radiobutton path="typedoc" value="profil"/>&nbsp;<form:label path="typedoc">PV de jury de suivi</form:label></dt>
  <dt><form:radiobutton path="typedoc" value="convoque"/>&nbsp;<form:label path="typedoc">PV des convoqués</form:label></dt>
  <dt><form:radiobutton path="typedoc" value="diplome"/>&nbsp;<form:label path="typedoc">PV des diplômables</form:label></dt>
</dl>
 
 <div class="action">
 	<input type="submit" value="${command.lib_action}"/>
 </div>
 
 <form:errors path="diplome" cssClass="erreur" /> 


 
</form:form>
</fieldset>

<c:if test="${!empty command.message}">
L'édition est en cours... Vous la recevrez par mail dans quelques instants...
</c:if> 
