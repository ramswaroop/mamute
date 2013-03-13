package br.com.caelum.brutal.auth.rules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.caelum.brutal.model.User;

public class ComposedRuleTest {
	private True TRUE = new True();
	private False FALSE = new False();

	@Test
	public void should_use_or() {
		ComposedRule<Void> composedRule = new ComposedRule<>();
		assertTrue(composedRule.thiz(TRUE).or(FALSE).isAllowed(null, null));
		assertTrue(composedRule.thiz(TRUE).or(TRUE).isAllowed(null, null));
		assertTrue(composedRule.thiz(FALSE).or(TRUE).isAllowed(null, null));
		assertFalse(composedRule.thiz(FALSE).or(FALSE).isAllowed(null, null));
	}
	
	@Test
	public void should_use_and() {
		ComposedRule<Void> composedRule = new ComposedRule<>();
		assertFalse(composedRule.thiz(TRUE).and(FALSE).isAllowed(null, null));
		assertTrue(composedRule.thiz(TRUE).and(TRUE).isAllowed(null, null));
		assertFalse(composedRule.thiz(FALSE).and(TRUE).isAllowed(null, null));
		assertFalse(composedRule.thiz(FALSE).and(FALSE).isAllowed(null, null));
	}
	
	static class True implements PermissionRule<Void> {
		@Override
		public boolean isAllowed(User u, Void item) {
			return true;
		}
	}
	
	static class False implements PermissionRule<Void> {
		@Override
		public boolean isAllowed(User u, Void item) {
			return false;
		}
	}

}
