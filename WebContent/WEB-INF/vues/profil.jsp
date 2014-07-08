<%@ page language="java" contentType="text/html; charset=ISO-8859-15"
	pageEncoding="ISO-8859-15" isELIgnored="false"%>
<%@ include file="/WEB-INF/vues/include.jsp"%>

<c:set var="etudiant" value="${etudiant}" />

<h5>Profil minimun de formation</h5>

<c:if test="${!empty pdf}">
	<div class="message">${pdf}</div>
</c:if>
<table cellpadding="0" cellspacing="0" class="resume">
	<tr>
		<td rowspan="5" style="vertical-align: middle"><img height="75"
			width="60" border="0" alt="photo de l'étudiant"
			src="http://local-sig.utt.fr/Pub/trombi/individu/mini/${etudiant.id}.jpg"
			onerror="this.src='images/photo/default.jpg'" /></td>
	</tr>
	<tr>
		<td>${etudiant.nom} ${etudiant.prenom} ${etudiant.email} N°<b>${etudiant.id}</b></td>
	</tr>
	<tr>
		<td><b>Né(e) le</b> ${etudiant.date_nais} à ${etudiant.com_nais}
		<b>BAC</b> ${etudiant.bac} en ${etudiant.an_bac}</td>
	</tr>
	<tr>
		<td><b>Conseiller</b> ${etudiant.conseiller} <b>Dernier
		diplôme obtenu</b> ${etudiant.der_dipl_obtenu}</td>
	</tr>
	<tr>
		<td><b>Dernier établissement</b> ${etudiant.etab_der_dipl} <b>Dernière
		inscription</b> ${etudiant.der_inscription} en ${etudiant.der_periode}</td>
	</tr>
</table>


<c:forEach var="diplome" items="${etudiant.diplomes}">
	<h4><a class="lienh4"
		href="javascript:cache('${diplome.id}${diplome.prd_debut}')">${diplome.libelle}
	de ${diplome.debut} à ${diplome.fin}</a></h4>
	<div class="show" id="${diplome.id}${diplome.prd_debut}"><c:if
		test="${! empty diplome.legende}">
		<div class="legende"><b>Légende</b> : <c:forEach var="legende"
			items="${diplome.legende}">
			<span class="symb symb${legende['LEGENDE']}">&#${legende['LEGENDE']};</span>&nbsp;
			${legende['PROFIL_LIB'] } &nbsp;&nbsp;&nbsp;&nbsp;
		</c:forEach></div>
	</c:if>

	<table
		class="profil <c:if test="${diplome.reoriente=='O'}">reoriente</c:if>"
		cellspacing="0" cellpadding="0" summary="profil">
		<tr>
			<td class="coin-sg"></td>
			<c:forEach var="categorie" items="${diplome.categories}">
				<th class="entete-haut ent-categ">${categorie['LIB_ABR']}</th>
			</c:forEach>
			<th class="coin-sg"></th>
		</tr>
		<c:forEach var="s" items="${diplome.inscriptions_adm}">
			<c:set var="cc" value="${s.an_univ}${s.prd_univ}" />
			<tr>
				<td class="entete-haut sem"><b>${s.libelle}</b><br/> ${s.formation}<br />
				${s.situation}<br />
				</td>
				<c:forEach var="c" items="${diplome.categories}">
					<td class="uvs">
					 <c:forEach var="uv"	items="${diplome.inscriptions_uv}">
						<c:if	test="${uv['PERIODE']==cc && uv['CATEG_ABR']==c['LIB_ABR']}">
							<div id="tip${cc}${c['LIB_ABR']}${uv['UV_$C']}" class="betterTip">
							<a href="uv.do?width=500&amp;uv=${uv['UV_$C']}&amp;per=${cc}"
								class="betterTip" id="uv${cc}${c['LIB_ABR']}${uv['UV_$C']}">
								${uv['UV_$C']}	${uv['RESUV_$C']} ${uv['CREDIT']} 
								<span	class="symb symb${uv['LEGENDE']}">&#${uv['LEGENDE']};</span></a>
							</div>
						</c:if>
					</c:forEach></td>
				</c:forEach>
				<td class="observation">
				<div>${s.observation}</div>
				<div class="decision">${s.decision}</div>
				</td>
			</tr>
			<tr class="stotal odd">
				<td class="lib-stotal"><b>Total semestre</b></td>
				<c:set var="st" value="${diplome.stotal}" />
				<c:forEach var="c" items="${diplome.categories}">
					<c:set var="cl" value="${cc}${c['LIB_ABR']}" />
					<c:set var="ts" value="${cc}TS" />
					<td class="stotal"><span>${st[cl]}</span></td>
				</c:forEach>
				<td class="totals">${st[ts]}</td>
			</tr>

		</c:forEach>
	</table>

	<div class="br"></div>
	<c:if test="${!empty diplome.totaux}">
		<h5>Totaux</h5>
		<table class="profil" cellspacing="0" cellpadding="0" summary="profil">
			<tr>
				<td class="coin-sg"></td>
				<c:forEach var="categorie" items="${diplome.categories}">
					<th class="entete-haut ent-categ">${categorie['LIB_ABR']}</th>
				</c:forEach>
				<td style="border: none"></td>
			</tr>

			<c:forEach var="t" items="${diplome.totaux}">
				<tr>
					<td class="entete-haut sem"><b>${t.libelle}</b></td>
					<c:forEach var="tt" items="${t.totaux}">
						<c:if test="${tt.total_id ==t.id}">
							<c:if test="${tt.ordre_lg==1 && tt.ordre_col!=999}">
								<td colspan="${tt.longueur}" align="center" ><span><b>${tt.total}<c:if
									test="${!empty tt.min}">/${tt.min}</c:if></b> <c:if
									test="${tt.acquis}">
									<img src="images/tick2.png" width="11" height="11" alt="acquis"
										border="0" />
								</c:if></span></td>
							</c:if>

						</c:if>
					</c:forEach>
					<td colspan="7" style="border: none"></td>
				</tr>

				<tr>
					<c:forEach var="tt" items="${t.totaux}" varStatus="statut">
						<c:if test="${tt.total_id ==t.id}">
							<c:if test="${tt.ordre_lg==2}">
								<td colspan="${tt.ordre_col}" style="border: none"></td>
								<td colspan="${tt.longueur}" align="center"><span><b>${tt.total}<c:if
									test="${!empty tt.min}">/${tt.min}</c:if></b> <c:if	test="${tt.acquis}">
									<img src="images/tick2.png" width="11" height="11" alt="acquis" border="0" />
								</c:if></span></td>
								<td colspan="6" style="border: none"></td>
							</c:if>
						</c:if>
					</c:forEach>
				</tr>


			</c:forEach>

		</table>
	</c:if></div>
</c:forEach>

<div class="br"></div>
