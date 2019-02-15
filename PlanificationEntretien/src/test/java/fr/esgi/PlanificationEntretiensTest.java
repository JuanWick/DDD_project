package fr.esgi;

import fr.esgi.exceptions.*;
import fr.esgi.infraStructure.outsideApi.*;
import fr.esgi.models.*;
import fr.esgi.useCases.PlanificationEntretiens;
import fr.esgi.useCases.impl.PlanificationEntretienBusiness;
import junit.framework.TestCase;
import org.junit.Assert;
import org.mockito.Mockito;

import java.util.*;

public class PlanificationEntretiensTest extends TestCase {
    ApiClient apiClient = Mockito.mock(ApiclientImpl.class);
    PersistanceService persistanceService = new PersistanceServiceTestImpl();

    PlanificationEntretiens planificationEntretiens = new PlanificationEntretienBusiness(apiClient,persistanceService);

    public void test_should_planifier_entretien()
    {
        //Creation du consultant recruteur cible
        List<Compétence> compétences = new ArrayList<>();
        compétences.add(Compétence.AGILITE);

        ConsultantRecruteur consultantRecruteur = ConsultantRecruteur.builder().Compétences(compétences).Nom("Consultant1").Id(0).build();

        //On mock le retour de l'API client
        List<ConsultantRecruteur> consultantRecruteurs = new ArrayList<>();
        consultantRecruteurs.add(consultantRecruteur);

        MoisCreneau moisCreneauRecherche = MoisCreneau.builder()
                .mois(3)
                .annee(2019).build();
        Mockito.when(apiClient.chercherConsultantRecruteurDisponibleParMois(moisCreneauRecherche)).thenReturn(consultantRecruteurs);

        //Creation du candidat
        Candidat candidat = Candidat.builder().Id(1).Nom("Test").Competences(compétences).build();

        //Creation du creneau horaire souhaité
        Calendar dateSouhaite = new GregorianCalendar(2019,3,5);
        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(dateSouhaite).heureDebut(10).build();

        //On execute la logique métier
        planificationEntretiens.planifierEntretien(candidat,creneauHoraire);

        //On vérifie que l'entretien est persisté
        Entretien entretien = persistanceService.getEntretien(candidat);

        Assert.assertEquals(entretien.getCandidat(), candidat);
        Assert.assertEquals(entretien.getConsultantRecruteur(), consultantRecruteur);
        Assert.assertEquals(entretien.getCreneauHoraire(), creneauHoraire);
    }

//    public void test_should_not_planifier_entretien_without_ConsultantRecruteur_with_competences()
//    {
//        List<Compétence> compétences = new ArrayList<>();
//        compétences.add(Compétence.AGILITE);
//        Candidat candidat = Candidat.builder().Id(1).Nom("Test").Competences(compétences).build();
//        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(new Date()).heureDebut(00).build();
//
//        Mockito.when(planificationEntretiens.planifierEntretien(candidat,creneauHoraire)).thenThrow(new UnMatchingCompetenceConsultantRecruteurException());
//
//        try {
//            planificationEntretiens.planifierEntretien(candidat,creneauHoraire);
//        } catch(Exception e){
//            Assert.assertEquals("Aucun consultant recruteur ne posséde la compétence requise.", e.getMessage());
//        }
//    }
//
//    public void test_should_not_planifier_entretien_when_ConsultantRecruteur_is_busy()
//     {
//         List<Compétence> compétences = new ArrayList<>();
//         compétences.add(Compétence.AGILITE);
//         Candidat candidat = Candidat.builder().Id(1).Nom("Test").Competences(compétences).build();
//         CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(new Date()).heureDebut(00).build();
//
//         Mockito.when(planificationEntretiens.planifierEntretien(candidat,creneauHoraire)).thenThrow(new BusyConsultantRecruteurException());
//
//         try {
//             planificationEntretiens.planifierEntretien(candidat,creneauHoraire);
//         } catch(Exception e){
//             Assert.assertEquals("Aucun consultant recruteur n'est disponible.", e.getMessage());
//         }
//     }
//
//     public void test_should_not_working_when_entretien_is_inferior_of_working_time(){
//        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(new Date()).heureDebut(05).build();
//         Candidat candidat = Candidat.builder().Id(12).Nom("Test").Competences(null).build();
//
//        Mockito.when(planificationEntretiens.planifierEntretien(candidat,creneauHoraire)).thenThrow(new HorairesOutOfWorkingTimeException());
//
//        try {
//            planificationEntretiens.planifierEntretien(candidat,creneauHoraire);
//        } catch(Exception e){
//            Assert.assertEquals("Les horaires de l'entretien sont en dehors du temps de travail", e.getMessage());
//        }
//    }
//
//    public void test_should_not_working_when_entretien_is_superior_of_working_time(){
//        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(new Date()).heureDebut(19).build();
//        Candidat candidat = Candidat.builder().Id(12).Nom("Test").Competences(null).build();
//        Mockito.when(planificationEntretiens.planifierEntretien(candidat,creneauHoraire)).thenThrow(new HorairesOutOfWorkingTimeException());
//
//        try {
//            planificationEntretiens.planifierEntretien(candidat,creneauHoraire);
//        } catch(Exception e){
//            Assert.assertEquals("Les horaires de l'entretien sont en dehors du temps de travail", e.getMessage());
//        }
//    }
//
//    public void test_should_not_working_when_entretien_date_is_null(){
//        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(null).build();
//        Candidat candidat = Candidat.builder().Id(12).Nom("Test").Competences(null).build();
//        Mockito.when(planificationEntretiens.planifierEntretien(candidat,creneauHoraire)).thenThrow(new DateIsNotValidException());
//
//        try {
//            planificationEntretiens.planifierEntretien(candidat,creneauHoraire);
//        } catch(Exception e){
//            Assert.assertEquals("La date est null", e.getMessage());
//        }
//    }
//
//    public void test_should_not_working_when_id_candidat_is_negativ() {
//        List<Compétence> compétences = new ArrayList<>();
//        compétences.add(Compétence.DOTNET);
//        Candidat candidat = Candidat.builder().Id(-1).Nom("Test").Competences(compétences).build();
//        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(new Date()).heureDebut(00).build();
//        Mockito.when(planificationEntretiens.planifierEntretien(candidat,creneauHoraire)).thenThrow(new IdNotValidException());
//
//        try {
//            planificationEntretiens.planifierEntretien(candidat,creneauHoraire);
//        } catch(Exception e){
//            Assert.assertEquals("L'Id n'est pas valide", e.getMessage());
//        }
//    }
//
//    public void test_should_not_working_when_id_candidat_equal_to_0() {
//        List<Compétence> compétences = new ArrayList<>();
//        compétences.add(Compétence.DOTNET);
//        Candidat candidat = Candidat.builder().Id(0).Nom("Test").Competences(compétences).build();
//        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(new Date()).heureDebut(00).build();
//        Mockito.when(planificationEntretiens.planifierEntretien(candidat,creneauHoraire)).thenThrow(new IdNotValidException());
//
//        try {
//            planificationEntretiens.planifierEntretien(candidat,creneauHoraire);
//        } catch(Exception e){
//            Assert.assertEquals("L'Id n'est pas valide", e.getMessage());
//        }
//    }
//
//    public void test_should_not_working_when_id_candidat_is_not_valid() {
//        List<Compétence> compétences = new ArrayList<>();
//        compétences.add(Compétence.DOTNET);
//        Candidat candidat = Candidat.builder().Id(99).Nom("Test").Competences(compétences).build();
//        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(new Date()).heureDebut(00).build();
//        Mockito.when(planificationEntretiens.planifierEntretien(candidat,creneauHoraire)).thenThrow(new IdNotValidException());
//
//        try {
//            planificationEntretiens.planifierEntretien(candidat,creneauHoraire);
//        } catch(Exception e){
//            Assert.assertEquals("L'Id n'est pas valide", e.getMessage());
//        }
//    }
//
//    public void test_should_failed_when_candidat_have_no_skills(){
//        Candidat candidat = Candidat.builder().Id(5).Nom("Test").Competences(null).build();
//        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(new Date()).heureDebut(00).build();
//        Mockito.when(planificationEntretiens.planifierEntretien(candidat,creneauHoraire)).thenThrow(new NoSkillsSelectedException());
//        try {
//            planificationEntretiens.planifierEntretien(candidat,creneauHoraire);
//        } catch(Exception e){
//            Assert.assertEquals("Aucune compétence sélectionnée", e.getMessage());
//        }
//    }


}
