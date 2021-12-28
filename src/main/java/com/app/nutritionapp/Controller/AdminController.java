package com.app.nutritionapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.nutritionapp.Exception.UserNotExistsException;
import com.app.nutritionapp.Model.CaloryLog;
import com.app.nutritionapp.Model.DietPlan;
import com.app.nutritionapp.Model.ExerciseModule;
import com.app.nutritionapp.Model.NutritionPlan;
import com.app.nutritionapp.Model.Payment;
import com.app.nutritionapp.Model.User;
import com.app.nutritionapp.Model.WeightLog;
import com.app.nutritionapp.Service.ICaloryLogService;
import com.app.nutritionapp.Service.IDietPlanService;
import com.app.nutritionapp.Service.IExerciseModuleService;
import com.app.nutritionapp.Service.INutritionPlanService;
import com.app.nutritionapp.Service.IPaymentService;
import com.app.nutritionapp.Service.IWeightLogService;
import com.app.nutritionapp.ServiceIMPL.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	//-----------------------------------USER-----------------------------------//
	@Autowired
	UserService uservice;
	
	/*@PostMapping("/users")
	public ResponseEntity<String> createUser(@RequestBody User user) {
		uservice.createUser(user);
		return new ResponseEntity<String> ("user added sucessfully",HttpStatus.OK);
	}*/

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> user =uservice.getallUsers();
		ResponseEntity<List<User>> res = new ResponseEntity<List<User>>(user, HttpStatus.OK);
		return res ;
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		User userData = uservice.getUser(id);

		if (userData !=null) {
			return new ResponseEntity<User>(userData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
		try {
			uservice.removeUser(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/users/{id}")
	public ResponseEntity<String> updateUser(long l, @RequestBody User user)
	{
	 uservice.updateUser(user.getUserId(),user); 
	
	return new ResponseEntity<String>("User updated successfully",HttpStatus.OK);
	}
	//-----------------------------------NutritionPlan------------------------------------------------------//
	@Autowired
	private INutritionPlanService planService;
	
	@PostMapping(value= "/plan")
	public ResponseEntity<String> addNutritionPlan(@RequestBody NutritionPlan plan) 
	{
		planService.addNutritionPlan(plan);
		return new ResponseEntity<String> ("NutritionPlan added successfully",HttpStatus.OK);
	}
		
	@DeleteMapping(value = "/plan/{userId}")
	public ResponseEntity<String> removeNutritionPlan(@PathVariable("userId") long userId) 
	{
		try {
		planService.removeNutritionPlan(userId);}
		
		catch(UserNotExistsException e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);}
		
		return new ResponseEntity<String>("NutritionPlan removed successfully",HttpStatus.OK);
	}
	
	@PutMapping(value = "/plan/{userId}")
	public ResponseEntity<String> updateNutritionPlan(@RequestBody NutritionPlan plan) 
	{
		planService.updateNutritionPlan(plan.getUserId(), plan);
		return new ResponseEntity<String>("NutritionPlan updated successfully",HttpStatus.OK);
	}
	
	@GetMapping(value = "/plan/{userId}")
	public ResponseEntity<Object> getNutritionPlan(@PathVariable("userId") long userId) //throws Exception
	{
		NutritionPlan plan;
		try {
			 plan =planService.getNutritionPlan(userId);}
			
		catch(UserNotExistsException e){
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);}
	
	        return new ResponseEntity<>(plan, HttpStatus.OK);
	}
	
	@GetMapping(value = "/plans")
	public ResponseEntity<List<NutritionPlan>> getAllNutritionPlans()
	{
		List<NutritionPlan> allp = planService.getallNutritionPlans();
		ResponseEntity<List<NutritionPlan>> res2 = new ResponseEntity<List<NutritionPlan>>(allp, HttpStatus.OK);
		return res2;
	}
	//-----------------------------------------Payment---------------------------------------------------------//
	
	@Autowired
    private IPaymentService paymentService;
	
	@PostMapping("/addpayment")
	public ResponseEntity <String> savePayments(@RequestBody Payment payment){
		paymentService.savePayment(payment);
		
	/*	if(payments.isEmpty()) {
			//return new ResponseEntity("Payment-info not available" , HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Payment>>(payments,HttpStatus.OK);    */
		return new ResponseEntity<String>("Payment saved successfully",HttpStatus.OK) ;
	}
		
	@GetMapping("/getAllpayments")
	public ResponseEntity<List<Payment>> getAllPayments(){
		
		List<Payment> payments = paymentService.getAllPayments();
		/* if(payments.isEmpty()) {
			return new ResponseEntity("Payment-info not available" , HttpStatus.NOT_FOUND);
		} */
	   ResponseEntity<List<Payment>> r = new ResponseEntity<List<Payment>>(payments,HttpStatus.OK);
	   return r;
	}
	
	@GetMapping("/getPaymentById/{id}")
	public ResponseEntity <Payment> getPayment(@PathVariable("id") long id){
		Payment payment = paymentService.getPayment(id);
		//if(payment == null){
			//return new ResponseEntity("Payment-info not available" , HttpStatus.NOT_FOUND);
	//	}
		ResponseEntity <Payment> r1 = new ResponseEntity <Payment> (payment , HttpStatus.OK);
		return r1;
	}

	//---------------------------------------------WeightLog-------------------------------------------------------//
	
	@Autowired
	private IWeightLogService  weightLogService;
	    
	@PostMapping("/addWeightLog")
    public ResponseEntity<String> addWeightLog(@RequestBody WeightLog weightLog) {
	    weightLogService.addWeightLog(weightLog); 
	    return new ResponseEntity<String>("Details added successfully",HttpStatus.OK);
	}
    
    @PutMapping("/updateWeightLog")
	public ResponseEntity<String> updateWeightLog(@RequestBody WeightLog weightLog) {
    	weightLogService.updateWeightLog(weightLog); 
	    return new ResponseEntity<String>("Details updated successfully",HttpStatus.OK);
	}

    @DeleteMapping("/delete/{unique_id}")
	public ResponseEntity<String> removeWeightLog(@PathVariable Long unique_id) {
    	weightLogService.removeWeightLog(unique_id); 
	    return new ResponseEntity<String>("Details deleted successfully",HttpStatus.OK);
	}

	@GetMapping("/getAllWeightLog")
	public ResponseEntity<List<WeightLog>> showAllWeightLog() {
		List<WeightLog> weightLogs=weightLogService.showAllWeightLog();
		ResponseEntity<List<WeightLog>> responseEntity=new ResponseEntity<List<WeightLog>>(weightLogs,HttpStatus.OK);
		return  responseEntity;
	}
	
	@GetMapping("/getWeightLogByName/{userName}")
	public ResponseEntity<List<WeightLog>> findUserByName(@PathVariable String userName) {
		List<WeightLog> weightLogs=weightLogService.findUserByName(userName);
		ResponseEntity<List<WeightLog>> responseEntity=new ResponseEntity<List<WeightLog>>(weightLogs,HttpStatus.OK);
		return  responseEntity;
	}
	//----------------------------------------------DietPlan------------------------------------------------------//
	
	@Autowired
	private IDietPlanService dietplanservice;

//	@PostMapping("/Splans")
//	public ResponseEntity<String> addDietPlan(@RequestBody DietPlan dietplan){
//		dietplanservice.addDietPlan(dietplan);
//		return new ResponseEntity<String>("Dietplan saved succesfully",HttpStatus.OK);
//	}

	@GetMapping("/Splans")
	public ResponseEntity<List<DietPlan>> getAllDietPlans()
	{
		List<DietPlan>dietplan1=dietplanservice.getAllDietPlans();
		ResponseEntity<List<DietPlan>> res2=new ResponseEntity<List<DietPlan>>(dietplan1,HttpStatus.OK);
		return res2;
	}
	
	@GetMapping("/plans/{id}")
	public ResponseEntity<DietPlan> getDietPlanById(@PathVariable("id") long dietplanId){
		DietPlan dietplan=dietplanservice.getDietPlanById(dietplanId);
		ResponseEntity<DietPlan> res1=new ResponseEntity<DietPlan>(dietplan,HttpStatus.OK);
		return res1;
	}

	@PutMapping("/plans")
	public ResponseEntity<String> updateDietPlan(@RequestBody DietPlan dietplan)
	{ 
		dietplanservice.updateDietPlan(dietplan.getId(), dietplan);
		return new ResponseEntity<String>("Dietplan updated successfull",HttpStatus.OK);
	}

	@DeleteMapping("/plans/{id}")
	public ResponseEntity<String> deleteDietPlan(@PathVariable("id") long id){
		dietplanservice.deleteDietPlan(id);
		return new ResponseEntity<String>("Dietplan deleted successfully!." , HttpStatus.OK);
	}
	//--------------------------------------------CaloryLog-----------------------------------------------------//

	@Autowired
	private ICaloryLogService caloryLogService;
	
	@GetMapping("/getByName/{userName}")
	public ResponseEntity<List<CaloryLog>> findUserByUsername(@PathVariable String userName) {
	List<CaloryLog> caloryLogs=caloryLogService.findUserByUsername(userName);   
    ResponseEntity<List<CaloryLog>> responseEntity=new ResponseEntity<List<CaloryLog>>(caloryLogs,HttpStatus.OK);
		return responseEntity;
	}
    
	@PostMapping("/add")
	public ResponseEntity<String> addCaloryLog(@RequestBody CaloryLog caloryLog) {
		caloryLogService.addCaloryLog(caloryLog); 
		return new ResponseEntity<String>("Details added successfully", HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateCaloryLog(@RequestBody CaloryLog caloryLog) {
		caloryLogService.updateCaloryLog(caloryLog);
		return new ResponseEntity<String>("Details updated successfully", HttpStatus.OK);
	}

	@DeleteMapping("/delete/{calory_id}")
	public ResponseEntity<String> removeCaloryLog(@PathVariable Integer calory_id) {
          caloryLogService.removeCaloryLog(calory_id);
          return new ResponseEntity<String>("Data removed successfully", HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllCaloryLog() {
		List<CaloryLog> caloryLogs=caloryLogService.getAllCaloryLog();   
        ResponseEntity<List<CaloryLog>> responseEntity=new  ResponseEntity<List<CaloryLog>>(caloryLogs,HttpStatus.ACCEPTED);
        return responseEntity;
	}
	
	@GetMapping("/totalSumCalories/{userName}")
	public ResponseEntity<Double> sumOfCalories(@PathVariable String userName) {
		 double totalCal=caloryLogService.sumOfCalories(userName);
		 return new ResponseEntity<Double>(totalCal,HttpStatus.OK);
	}
	//----------------------------------------Exercise-------------------------------------------------------//
	
	@Autowired
	private IExerciseModuleService exerciseModuleService;


	@PostMapping(value= "/module")
	public ResponseEntity<String> addExerciseModule(@RequestBody ExerciseModule module)
	{
		exerciseModuleService.saveExerciseModule(module);
		return new ResponseEntity<String>("ExerciseModule added successfully",HttpStatus.OK);
	}
		

	@DeleteMapping(value = "/module/{userId}")
	public ResponseEntity<String> removeExerciseModule(@PathVariable("userId")int userId)
	{
		    exerciseModuleService.deleteExerciseModule(userId);
		return new ResponseEntity<String>("ExerciseModule removed successfully", HttpStatus.OK);
	}

	@PutMapping(value = "/module")
	public ResponseEntity<String> updateExerciseModule(@RequestBody ExerciseModule module)
	{
		exerciseModuleService.updateExerciseModule(module.getUserId(), module);
		return new ResponseEntity<String>("ExerciseModule updated successfully",HttpStatus.OK);
	}


	@GetMapping(value ="/module/{userId}")
	public ResponseEntity<ExerciseModule> getExerciseModule(@PathVariable ("userId") int userId) throws Exception
	{
		
		//ExerciseModule module = moduleService.getExerciseModule(userId);
		//if(module==null)
		//{
	    //return new ResponseEntity("Sorry no tasks found with this ID", HttpStatus.NOT_FOUND);
		//}
		//return new ResponseEntity<ExerciseModule>(module, HttpStatus.OK);
		
		ExerciseModule rep = exerciseModuleService.findExerciseModule(userId);
		ResponseEntity<ExerciseModule> res1 = new ResponseEntity<ExerciseModule>(rep, HttpStatus.OK);
		return res1;
	}

	   @GetMapping(value = "/module")
	   public ResponseEntity<List<ExerciseModule>> getAllExerciseModule()
	   {
		   List<ExerciseModule> allrep = exerciseModuleService.getAllExerciseModule();
	       ResponseEntity<List<ExerciseModule>> res2 = new ResponseEntity<List<ExerciseModule>>(allrep, HttpStatus.OK);
	       return res2;
	       
	   }
	
}
