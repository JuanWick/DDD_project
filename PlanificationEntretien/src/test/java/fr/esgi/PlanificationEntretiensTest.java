package fr.esgi;

import fr.esgi.exceptions.*;
import fr.esgi.useCases.PlanificationEntretiens;
import fr.esgi.useCases.impl.PlanificationEntretienBusiness;
import fr.esgi.models.Candidat;
import fr.esgi.models.Compétence;
import fr.esgi.models.CreneauHoraire;
import junit.framework.TestCase;
import org.junit.Assert;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlanificationEntretiensTest extends TestCase {
    PlanificationEntretiens planificationEntretiens = Mockito.mock(PlanificationEntretienBusiness.class);

    public void test_should_planifier_entretien()
    {
        List<Compétence> compétences = new ArrayList<>();
        compétences.add(Compétence.AGILITE);
        Candidat candidat = Candidat.builder().Id(1).Nom("Test").Competences(compétences).build();
        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(new Date()).heureDebut(00).build();

        Mockito.when(planificationEntretiens.planifierEntretien(candidat,creneauHoraire)).thenReturn(true);


        Assert.assertTrue(planificationEntretiens.planifierEntretien(candidat,creneauHoraire));
    }

    public void test_should_not_planifier_entretien_without_ConsultantRecruteur_with_competences()
    {
        List<Compétence> compétences = new ArrayList<>();
        compétences.add(Compétence.AGILITE);
        Candidat candidat = Candidat.builder().Id(1).Nom("Test").Competences(compétences).build();
        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(new Date()).heureDebut(00).build();

        Mockito.when(planificationEntretiens.planifierEntretien(candidat,creneauHoraire)).thenThrow(new UnmatchingCompetenceConsultantRecruteur());

        try {
            planificationEntretiens.planifierEntretien(candidat,creneauHoraire);
        } catch(Exception e){
            Assert.assertEquals("Aucun consultant recruteur ne posséde la compétence requise.", e.getMessage());
        }
    }

    public void test_should_not_planifier_entretien_when_ConsultantRecruteur_is_busy()
     {
         List<Compétence> compétences = new ArrayList<>();
         compétences.add(Compétence.AGILITE);
         Candidat candidat = Candidat.builder().Id(1).Nom("Test").Competences(compétences).build();
         CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(new Date()).heureDebut(00).build();

         Mockito.when(planificationEntretiens.planifierEntretien(candidat,creneauHoraire)).thenThrow(new BusyConsultantRecruteur());

         try {
             planificationEntretiens.planifierEntretien(candidat,creneauHoraire);
         } catch(Exception e){
             Assert.assertEquals("Aucun consultant recruteur n'est disponible.", e.getMessage());
         }
     }

     public void test_should_not_working_when_entretien_is_inferior_of_working_time(){
        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(new Date()).heureDebut(05).build();
         Candidat candidat = Candidat.builder().Id(12).Nom("Test").Competences(null).build();

        Mockito.when(planificationEntretiens.planifierEntretien(candidat,creneauHoraire)).thenThrow(new HorairesOutOfWorkingTime());

        try {
            planificationEntretiens.planifierEntretien(candidat,creneauHoraire);
        } catch(Exception e){
            Assert.assertEquals("Les horaires de l'entretien sont en dehors du temps de travail", e.getMessage());
        }
    }

    public void test_should_not_working_when_entretien_is_superior_of_working_time(){
        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(new Date()).heureDebut(19).build();
        Candidat candidat = Candidat.builder().Id(12).Nom("Test").Competences(null).build();
        Mockito.when(planificationEntretiens.planifierEntretien(candidat,creneauHoraire)).thenThrow(new HorairesOutOfWorkingTime());

        try {
            planificationEntretiens.planifierEntretien(candidat,creneauHoraire);
        } catch(Exception e){
            Assert.assertEquals("Les horaires de l'entretien sont en dehors du temps de travail", e.getMessage());
        }
    }

    public void test_should_not_working_when_entretien_date_is_null(){
        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(null).build();
        Candidat candidat = Candidat.builder().Id(12).Nom("Test").Competences(null).build();
        Mockito.when(planificationEntretiens.planifierEntretien(candidat,creneauHoraire)).thenThrow(new DateIsNotValid());

        try {
            planificationEntretiens.planifierEntretien(candidat,creneauHoraire);
        } catch(Exception e){
            Assert.assertEquals("La date est null", e.getMessage());
        }
    }

    public void test_should_not_working_when_id_candidat_is_negativ() {
        List<Compétence> compétences = new ArrayList<>();
        compétences.add(Compétence.DOTNET);
        Candidat candidat = Candidat.builder().Id(-1).Nom("Test").Competences(compétences).build();
        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(new Date()).heureDebut(00).build();
        Mockito.when(planificationEntretiens.planifierEntretien(candidat,creneauHoraire)).thenThrow(new IdNotValid());

        try {
            planificationEntretiens.planifierEntretien(candidat,creneauHoraire);
        } catch(Exception e){
            Assert.assertEquals("L'Id n'est pas valide", e.getMessage());
        }
    }

    public void test_should_not_working_when_id_candidat_equal_to_0() {
        List<Compétence> compétences = new ArrayList<>();
        compétences.add(Compétence.DOTNET);
        Candidat candidat = Candidat.builder().Id(0).Nom("Test").Competences(compétences).build();
        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(new Date()).heureDebut(00).build();
        Mockito.when(planificationEntretiens.planifierEntretien(candidat,creneauHoraire)).thenThrow(new IdNotValid());

        try {
            planificationEntretiens.planifierEntretien(candidat,creneauHoraire);
        } catch(Exception e){
            Assert.assertEquals("L'Id n'est pas valide", e.getMessage());
        }
    }

    public void test_should_not_working_when_id_candidat_is_not_valid() {
        List<Compétence> compétences = new ArrayList<>();
        compétences.add(Compétence.DOTNET);
        Candidat candidat = Candidat.builder().Id(99).Nom("Test").Competences(compétences).build();
        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(new Date()).heureDebut(00).build();
        Mockito.when(planificationEntretiens.planifierEntretien(candidat,creneauHoraire)).thenThrow(new IdNotValid());

        try {
            planificationEntretiens.planifierEntretien(candidat,creneauHoraire);
        } catch(Exception e){
            Assert.assertEquals("L'Id n'est pas valide", e.getMessage());
        }
    }

    public void test_should_failed_when_candidat_have_no_skills(){
        Candidat candidat = Candidat.builder().Id(5).Nom("Test").Competences(null).build();
        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(new Date()).heureDebut(00).build();
        Mockito.when(planificationEntretiens.planifierEntretien(candidat,creneauHoraire)).thenThrow(new NoSkillsSelected());
        try {
            planificationEntretiens.planifierEntretien(candidat,creneauHoraire);
        } catch(Exception e){
            Assert.assertEquals("Aucune compétence sélectionnée", e.getMessage());
        }
    }


}
