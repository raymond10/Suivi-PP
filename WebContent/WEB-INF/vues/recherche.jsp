<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15"%>
<%@ include file="/WEB-INF/vues/include.jsp" %>
<%@ page isELIgnored="false" %>



<fieldset>
<legend>Recherche d'étudiants</legend>
<div class="aide info">
La recherche peut s'effectuer :
<ul>
	<li>par diplôme, période, niveau ou UV</li>
	<li>ou par nom</li>
	<li>ou par numéro</li>
</ul>
</div>


<form:form method="post" action="${command.url_action}">
	<form:hidden path="id" />
	<form:hidden path="action" />
	
 <form:label path="diplome" cssClass="label1">Diplôme</form:label>
 <html:select path="command.diplome" size="1"  onchange="javascript:changeFiltre(this)" >
  <option value="">Tous</option> 
 	<html:options items="${listeDiplomes}"		value="id" label="libelle"  selected="${command.diplome}" addEmptyOption="false"/>
 </html:select>
 

 <form:label path="periode" cssClass="label">Période</form:label>
 
 <html:select path="command.periode" size="1"  onchange="javascript:changeFiltre(this)" >
  <option value="">Toutes</option> 
 	<html:options items="${listePeriodes}"		value="id" label="libelle"  selected="${command.periode}" addEmptyOption="false"/>
 </html:select>  
 <form:errors path="periode" cssClass="erreur" />
 
 <form:label path="niveau" cssClass="label">Niveau</form:label>
 <html:select path="command.niveau" size="1"  onchange="javascript:changeFiltre(this)" >
  <option value="">Tous</option> 
 	<html:options items="${listeNiveaux}"		value="id" label="libelle"  selected="${command.niveau}" addEmptyOption="false"/>
 </html:select>   
 <form:errors path="niveau" cssClass="erreur" />
 
 <label class="label">Situation</label>
 <html:select path="command.situation" size="1">
  <option value="">Toutes</option> 
 	<html:options items="${listeSituations}"		value="id" label="libelle"  selected="${command.situation}" addEmptyOption="false"/>
 </html:select>   
 
 <div style="margin:6px 0 6px 0;height:1px;"></div>
 
 <label class="label1">UV</label>
 <html:select path="command.uv" size="1">
  <option value="">Toutes</option> 
 	<html:options items="${listeUVs}"		value="id" label="libelle"  selected="${command.uv}" addEmptyOption="false"/>
 </html:select>   
	<hr/>
<c:if test="${command.action=='recherche' or command.action=='filtre'}">
	<form:label path="nom" cssClass="label1">ou Nom</form:label>
 <form:input path="nom" cssClass="text" maxlength="10" />
 <form:errors path="nom" cssClass="erreur" /> 
  <hr/>
 <form:label path="numero" cssClass="label1">ou Numéro</form:label>
 <form:input path="numero" cssClass="text" maxlength="10" />
 <form:errors path="numero" cssClass="erreur" />
<c:if test="${command.conseiller}">
 <hr/>
 <form:checkbox path="etu_conseilles"  value="true" cssStyle="padding-left:0.5em;margin-bottom:0px" />
 <form:label path="etu_conseilles" cssClass="label">Etudiants dont vous êtes le conseillé ce semestre (${command.lib_pr_conseiller})</form:label>
 <form:errors path="etu_conseilles" cssClass="erreur" /> 
</c:if>
</c:if> 


 <div class="action">
 	<input type="submit" value="${command.lib_action}"/>
 </div>
 
 <form:errors path="diplome" cssClass="erreur" /> 


 
</form:form>
</fieldset>


<c:if test="${command.type_action=='recherche'}">
<c:if test="${!empty etudiants and command.action!='filtre'}">

<c:if test="${etudiants!=null && etudiants.nrOfElements>0}">
<span><b>Résultats de la recherche :</b></span>
<div class="navi">

<span><b>${etudiants.nrOfElements}</b> étudiants trouvés.&nbsp;</span>
<c:if test="${!etudiants.firstPage}">
&nbsp;&nbsp;<span ><a href="rechercheEtudiant.do?page=prec"><b>&#x00AB; précédents</b></a></span>
</c:if>
<c:if test="${!(etudiants.firstPage || etudiants.lastPage)}">|</c:if>

<c:if test="${!etudiants.lastPage}">
	<span><a href="rechercheEtudiant.do?page=suiv"><b>suivants &#x00BB;</b></a></span>
</c:if>
&nbsp;&nbsp;<span class="ce1">Résultats <b>${etudiants.firstElementOnPage+1}</b> à <b>${etudiants.lastElementOnPage+1}</b></span>
</div>
</c:if>

<div style="width:100%">
<table cellspacing="0" cellpadding="0" border="0" class="listeR" >
<c:forEach var="etudiant" items="${etudiants.pageList}" >
<c:set var="co" value="${!co}"/>
<tr <c:if test="${co}">class="pair"</c:if> >
	<td width="80px" align="left"><img height="75"
			width="60" border="0" alt="photo de l'étudiant"
			src="http://local-sig.utt.fr/Pub/trombi/individu/mini/${etudiant.id}.jpg"
			onerror="this.src='images/photo/default.jpg'" />
	</td>
	<td align="left" width="575px">
	<div><b>${etudiant.civ} ${etudiant.prenom} ${etudiant.nom}</b> ${etudiant.date_nais} 
	<a href="mailto:${etudiant.email}">${etudiant.email}</a> N°<b>${etudiant.id}</b></div>
		<ul>
	<c:forEach var="d" items="${etudiant.diplomes}">
	<li>${d.libelle} de ${d.debut} à ${d.fin} 
	<c:if test="${!empty d.situation}">- <b>${d.situation}</b></c:if> 
	<c:if test="${!empty d.num_diplome}">- diplomé N°<b>${d.num_diplome}</b></c:if>
	</li>
	</c:forEach>
	</ul>
	</td>
	<td align="left">
		<ul>
		<li><a href="profil.do?id=${etudiant.id}">son profil</a></li>
		<li><a href="profil.do?id=${etudiant.id}">ses inscriptions administratives</a></li>
		<li><a href="profil.do?id=${etudiant.id}">son emploi du temps</a></li>
		<li><a href="profil.do?id=${etudiant.id}">ses stages</a></li>
	</ul>
	</td>
</tr>	
</c:forEach>
</table>
</div>
<c:if test="${etudiants!=null && etudiants.nrOfElements>0}">

<div class="action navi">
<span><b>${etudiants.nrOfElements}</b> étudiants trouvés.&nbsp;</span>
<c:if test="${!etudiants.firstPage}">
&nbsp;&nbsp;<span ><a href="rechercheEtudiant.do?page=prec"><b>&#x00AB; précédents</b></a></span>
</c:if>

<c:if test="${!(etudiants.firstPage || etudiants.lastPage)}">|</c:if>
<c:if test="${!etudiants.lastPage}">
	<span><a href="rechercheEtudiant.do?page=suiv"><b>suivants &#x00BB;</b></a></span>
</c:if>
&nbsp;&nbsp;<span class="ce1">Résultats <b>${etudiants.firstElementOnPage+1}</b> à <b>${etudiants.lastElementOnPage+1}</b></span>
</div>
</c:if>

</c:if>
</c:if>

