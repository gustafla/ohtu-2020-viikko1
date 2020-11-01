fn main() {
    let output = std::process::Command::new("./hello1").output().unwrap();
    let string = String::from_utf8(output.stdout).unwrap();
    assert_eq!(string, "Hello");
}
