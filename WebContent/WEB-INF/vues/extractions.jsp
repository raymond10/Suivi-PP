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
<legend>Recherche d'étudiants</legend>
<div class="info">
La recherche peut s'effectuer :
<ul>
	<li>par diplôme, période, niveau ou UV</li>
</ul>
</div>


<form:form method="post" action="${command.url_action}">
	<form:hidden path="id" />
	<form:hidden path="action" />

 <form:label path="periode" cssClass="label1">Période</form:label>
 <html:select path="command.periode" size="1"  onchange="javascript:changeFiltre(this)" >
  <!-- <option value="">Toutes</option> -->
 	<html:options items="${listePeriodes}"		value="id" label="libelle"  selected="${command.periode}" addEmptyOption="false"/>
 </html:select>  
 <form:errors path="periode" cssClass="erreur" />
	
 <form:label path="diplome" cssClass="label">Diplôme</form:label>
 <html:select path="command.diplome" size="1"  onchange="javascript:changeFiltre(this)" >
  <option value="">Tous</option>
 	<html:options items="${listeDiplomes}"		value="id" label="libelle"  selected="${command.diplome}" addEmptyOption="false"/>
 </html:select>
 
 
 <form:label path="niveau" cssClass="label">Niveau</form:label>
 <html:select path="command.niveau" size="1"  onchange="javascript:changeFiltre(this)" >
  <option value="">Tous</option> 
 	<html:options items="${listeNiveaux}"		value="id" label="libelle"  selected="${command.niveau}" addEmptyOption="false"/>
 </html:select>   
 <form:errors path="niveau" cssClass="erreur" />
 <label class="label">UV</label><select name="uv"><option value="" disabled="">Aucun</option></select>
	<hr/>

 
 <div class="action">
 	<input type="submit" value="${command.lib_action}"/>
 </div>
 
 <form:errors path="diplome" cssClass="erreur" /> 


</form:form>
</fieldset>

