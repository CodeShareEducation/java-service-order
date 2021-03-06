/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.codeshare.service;

import javax.ejb.Stateless;

import br.com.codeshare.data.AbstractRepository;
import br.com.codeshare.model.Client;
import br.com.codeshare.model.Member;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class MemberRegistration extends AbstractRepository<Client>{

    public void register(Member member) throws Exception {
        log.info("Registering " + member.getName());
        getEntityManager().persist(member);
    }
    
    public void remove(Member member){
    	log.info("Removing " + member.getName());
        getEntityManager().merge(member);
        getEntityManager().remove(member);
    }
    
    public Member update(Member member){
    	log.info("Updating " + member.getName());
    	Member merge = getEntityManager().merge(member);
        getEntityManager().persist(merge);
    	return merge;
    }
}
