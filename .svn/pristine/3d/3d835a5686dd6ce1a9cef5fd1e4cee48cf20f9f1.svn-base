<%@ page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15"%>
<%@ include file="/WEB-INF/vues/include.jsp" %>

<c:set var="uv" value="${uv}"/>

<div class="tip-uv" id="tip-uv">
<c:choose>
<c:when test="${!empty uv}">
<div><h3><b>${uv.uv}</b> - ${uv.titre}</h3></div>
<div><h5>Objectif </h5><p class="tip-p">${uv.objectif}</p></div>
<div><h5>Programme </h5><p class="tip-p">${uv.programme }</p></div>
<div><h5>Charges de travail </h5><p class="tip-p">
<c:forEach var="charge" items="${uv.chargeTrav}">
<div class="tip-txt">&nbsp;<b>${charge.libelle }</b>&nbsp;${charge.nbheures}h.</div>
</c:forEach>
</p>
<div><h5>Enseignée au(x) </h5><p class="tip-p">
<c:forEach var="perens" items="${uv.perEnseignement}">
<div class="tip-txt">&nbsp;<b>${perens.libelle }</b></div>
</c:forEach>
</p>
<div><h5>Antécédents</h5><p class="tip-p">
<c:forEach var="ante" items="${uv.antecedents}">
<div class="tip-txt">&nbsp;<b>${ante.libelle }</b></div>
</c:forEach>
</p>
</div>

</c:when>
<c:when test="${empty uv}">
<div class="warn"><b>Description non disponible.</b></div>
</c:when>
</c:choose>
</div>