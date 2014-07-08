<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15"%>
<%@ include file="/WEB-INF/vues/include.jsp" %>
<%@ page isELIgnored="false" %>


<div class="rechercheUV">
<div class="form">
<h5><a href="#" class="ah5">Recherche simple</a> | <a href="#" class="ah5">Recherche avancée</a></h5>
<div class="info">
Pour rechercher une UV ou un module, vous pouvez indiquer son code et éventuellement 
un diplôme, une spécialité ou une catégorie de recherche.<br/>
<b>La recherche simple s'effectue sur le catalogue de l'année en cours : ${annee}</b>
</div>
<form:form action="rechercheUV.do" cssClass="form-s">
	<form:hidden path="action" />
<div class="item">	
	<form:label path="code" cssClass="label">Code UV</form:label>
 	<form:input path="code" cssClass="saisie maj" maxlength="8" size="10"/>
 	<span class="mes-aide">(ex : MT21)</span>
  <form:errors path="code" cssClass="erreur" />

<span class="abc">
<a href="javascript:void(0)" onclick="javascript:changeUV(this)">A</a>
<a href="javascript:void(0)" onclick="javascript:changeUV(this)">B</a>
<a href="javascript:void(0)" onclick="javascript:changeUV(this)">C</a>
<a href="javascript:void(0)" onclick="javascript:changeUV(this)">D</a>
<a href="javascript:void(0)" onclick="javascript:changeUV(this)">E</a>
<a href="javascript:void(0)" onclick="javascript:changeUV(this)">F</a>
<a href="javascript:void(0)" onclick="javascript:changeUV(this)">G</a>
<a href="javascript:void(0)" onclick="javascript:changeUV(this)">H</a>
<a href="javascript:void(0)" onclick="javascript:changeUV(this)">I</a>
<a href="javascript:void(0)" onclick="javascript:changeUV(this)">J</a>
<a href="javascript:void(0)" onclick="javascript:changeUV(this)">K</a>
<a href="javascript:void(0)" onclick="javascript:changeUV(this)">L</a>
<a href="javascript:void(0)" onclick="javascript:changeUV(this)">M</a>
<a href="javascript:void(0)" onclick="javascript:changeUV(this)">O</a>
<a href="javascript:void(0)" onclick="javascript:changeUV(this)">P</a>
<a href="javascript:void(0)" onclick="javascript:changeUV(this)">Q</a>
<a href="javascript:void(0)" onclick="javascript:changeUV(this)">R</a>
<a href="javascript:void(0)" onclick="javascript:changeUV(this)">S</a>
<a href="javascript:void(0)" onclick="javascript:changeUV(this)">T</a>
<a href="javascript:void(0)" onclick="javascript:changeUV(this)">U</a>
<a href="javascript:void(0)" onclick="javascript:changeUV(this)">V</a>
<a href="javascript:void(0)" onclick="javascript:changeUV(this)">X</a>
<a href="javascript:void(0)" onclick="javascript:changeUV(this)">Y</a>
<a href="javascript:void(0)" onclick="javascript:changeUV(this)">Z</a>
</span>  

</div>

<div class="item">

	<form:label path="diplome" cssClass="label">Diplôme</form:label>
 <form:select path="diplome" size="1" multiple="false" cssClass="llov" onchange="javascript:changeFiltre(this)" cssStyle="width:215px">
     <option style="color:#C0C0C0;" value="">Choisir un diplôme...</option>
     <form:options items="${listeDiplomes}" itemValue="id" itemLabel="libelle" />
 </form:select>
  <form:errors path="diplome" cssClass="erreur" />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<form:label path="specialite" cssClass="label1">Spécialité&nbsp;&nbsp;</form:label>
 <form:select path="specialite" size="1" multiple="false" cssClass="llov" >
      <option style="color:#C0C0C0;" value="">Choisir une spécialité...</option>
     <form:options items="${listeSpecialites}" itemValue="id" itemLabel="libelle" />
 </form:select>
  <form:errors path="specialite" cssClass="erreur" />
</div>

<div class="item">
 <form:label path="categorie" cssClass="label">Catégorie</form:label>
 <form:select path="categorie" size="1" multiple="false" cssClass="llov" cssStyle="width:215px">
   <option style="color:#C0C0C0;" value="">Choisir une catégorie...</option>
   <form:options items="${listeCategories}" itemValue="id" itemLabel="libelle" />
 </form:select>
 <form:errors path="categorie" cssClass="erreur" />
</div>
<div class="action"> 
<input type="submit" value="Rechercher" class="bouton"/>
&nbsp;&nbsp;&nbsp;<a href="rechercheUV.do?action=nouv" >Nouvelle recherche</a>
</div>

</form:form>
</div>

<BR/><BR/><BR/>


<c:if test="${!empty uvs}">
<ec:table items="uvs"
	action="${pageContext.request.contextPath}/rechercheUV.do"
	imagePath="${pageContext.request.contextPath}/images/table/*.gif"
	width="98%" rowsDisplayed="50" filterable="false">

	<ec:exportXls view="xls" fileName="output.xls" tooltip="Export Excel" />
	<ec:row>
		<ec:column property="uv" title="UV"/>
		<ec:column property="titre" />
		<ec:column property="programme" />
		<ec:column property="objectif" />
	</ec:row>
</ec:table>
</c:if>
<c:if test="${empty uvs}">
<div>${message}</div>
</c:if>


<c:if test="${myuvs!=null && myuvs.nrOfElements>0}">
<div class="action navi">
<span><b>${myuvs.nrOfElements}</b> références trouvées.&nbsp;</span>
<span class="ce1">Résultats <b>${myuvs.firstElementOnPage+1}</b> à <b>${myuvs.lastElementOnPage+1}</b></span>
<c:if test="${!myuvs.firstPage}">
	<span ><a href="rechercheUV.do?page=prec"><b>&#x00AB; précédents</b></a></span>
</c:if>
<c:if test="${!(myuvs.firstPage || myuvs.lastPage)}">|</c:if>
<c:if test="${!myuvs.lastPage}">
	<span><a href="rechercheUV.do?page=suiv"><b>suivants &#x00BB;</b></a></span>
</c:if>
<span class="ce2"><a href="rechercheUV.do?action=atout">Tout ajouter</a></span>
<span class="cata-export ce3">
<b>Exports :</b> <a href="rechercheUV.do?export=pdf" class="pdf" target="_blank">pdf</a>&nbsp;<a href="rechercheUV.do?export=xls" class="csv" target="_blank">xls</a>
</span>

</div>
<br/>

<table cellpadding="0" cellspacing="0" border="0" class="listeUV">
<c:forEach var="uu" items="${myuvs.pageList}" varStatus="ii">
<tr <c:if test="${ii.last}">class="last"</c:if> ><td  class="laction betterTip" >
<a href="rechercheUV.do?ap=${uu.uv }" title="ajouter à la sélection"><img src="images/add.png" height="16" width="16" border="0" alt="ajouter à la sélection"/></a>
<a href="uv.do?width=500&uv=${uu.uv}&per=${annee}" class="betterTip" id="uv${uu.uv}" ><img src="images/zoom_in.png" height="16" width="16" border="0" alt="voir le détail"/></a>
</td>
<td class="contenu">
<b>${uu.uv} - ${uu.titre}</b> <br/>${uu.programme}<br/> ${uu.objectif}
</td>
</tr>
</c:forEach>
</table>
</c:if>

<br/><br/>

</div>
