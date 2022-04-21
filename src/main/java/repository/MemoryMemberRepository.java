package repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;


    @Override
    public Member save(Member member) {
         member.setId(++sequence);
         store.put(member.getId(), member);
         return member;
    }

    @Override
    public Optional<Member> findById(Long id) {

        return Optional.ofNullable(store.get(id));
        // Optional wrapper클래스를 사용하여
        // 객체가 존재하지 않아 null값이 리턴 될 수도 있을 경우를 대비함.
    }

    @Override
    public Optional<Member> findByName(String name) {
            return store.values().stream() //map의 밸류 값들 전체에 대해 루핑
                    .filter(member -> member.getName().equals(name))//그중 하나가 파라미터로 넘어온 name과 같으면
                    .findAny();//하나라도 있으면 반환함.
            //알아서 Optional로 반환된다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        //store의 valuer값들을 어레이리스트에 담는다.
    }

    public void clearStore(){
        store.clear();
    }

}
