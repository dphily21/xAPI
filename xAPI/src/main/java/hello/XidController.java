package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import hello.Xid;
import hello.XidRepository;

import java.util.Optional;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/xid") // This means URL's start with /demo (after Application path)
public class XidController {
	@Autowired // This means to get the bean called userRepository
	// Which is auto-generated by Spring, we will use it to handle the data
	private XidRepository xidRepository;

	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewXid (@RequestParam String subset_name) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Xid x = new Xid();
		x.setSubset_name(subset_name);
		xidRepository.save(x);
		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Xid> getAllXids() {
		// This returns a JSON or XML with the users
		return xidRepository.findAll();
	}
	@GetMapping(path="/{subset_xid_id}")
	public @ResponseBody
	Optional<Xid> getOneXid(@PathVariable Integer subset_xid_id){
		return xidRepository.findById(subset_xid_id);
	}
}
