//package HJproject.Hellospring.repository;
//
//import HJproject.Hellospring.domain.member.Member;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//public class JdbcTemplateMemberRepository implements MemberRepository{
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public JdbcTemplateMemberRepository(DataSource dataSource){
//        jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//    // RowMapper
//    private RowMapper<Member> memberRowMapper(){
//        // 아래에서 사용되는 RowMapper 객체 내용은 여기서 생성되서 넣어짐
//
//        // 기본 코드
////        return new RowMapper<Member>() {
////            @Override
////            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
////
////                Member member = new Member();
////                member.setCode(rs.getLong("code"));
////                member.setName(rs.getString("name"));
////                return member;
////            }
//
//        // 람다식으로 바꾼 코드
//        return (rs, rowNum) -> {
//
//            Member member = new Member();
//            member.setMEMBERCODE(rs.getLong("member_code"));
//            member.setMNAME(rs.getString("name"));
//
//            // 내 맘대로 구현하기
//            member.setMID(rs.getString("id"));
//            member.setMPASSWD(rs.getString("passwd"));
//
//            return member;
//        };
//    }
//
//
//    @Override // jdbcTemplate 이용해서 DB 에 정보 저장
//    public Member save(Member member) {
//        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
//        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("member_code");
//        // usingGeneratedKeyColumns : code 가 자동으로 입력되도록 함 -> 보통 DB에서 auto_increment 해서 자동으로 생성되고, 여기에 그 값이 자동으로 입력됨
//
//
//        Map<String, Object> parameters = new HashMap<>();
//
//        parameters.put("name", member.getMNAME()); // name 컬럼에 member.getName 넣기
//
//        // 5. 내 맘대로 구현하기
//        parameters.put("id", member.getMID()); // id 컬럼에 member.getId 넣기
//        parameters.put("passwd", member.getMPASSWD());
//
//
//        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
//        member.setMEMBERCODE(key.longValue());
//        return member;
//    }
//
//    @Override // SQL 쿼리를 사욯하기 위해서는 RowMapper 를 이용한다.
//    public Optional<Member> findBycode(Long membercode) {  // code 로 회원 찾기 : memberRowMapper() 객체의 code 부분
//        List<Member> result = jdbcTemplate.query("select * from member where code = ?", memberRowMapper(), membercode);
//
//        return result.stream().findAny();
//    }
//
//    @Override
//    public Optional<Member> findByName(String name) { // 이름으로 회원 찾기
//        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
//
//        return result.stream().findAny();
//    }
//
//    @Override
//    public Optional<Member> findById(String ID) { // code 로 회원 찾기 : memberRowMapper() 객체의 id 부분
//        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), ID);
//
//        return result.stream().findAny();
//    }
//
//    @Override
//    public Optional<Member> findByPasswd(String passwd) {
//        List<Member> result = jdbcTemplate.query("select * from member where passwd = ?", memberRowMapper(), passwd);
//
//        return result.stream().findAny();
//    }
//
//    @Override
//    public Optional<Member> findBySex(String sex) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Member> findByEmail(String email) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Member> findByEmaddress(String emaddress) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Member> findByRData(String RData) {
//        return Optional.empty();
//    }
//
//    @Override
//    public List<Member> findAll() {
//        return jdbcTemplate.query("select * from member", memberRowMapper());
//    }
//
//
//}
