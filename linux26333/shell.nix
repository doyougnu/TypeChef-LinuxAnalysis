with import <nixpkgs> {}; {
  qpidEnv = stdenvNoCC.mkDerivation {
    name = "my-gcc8-environment";
    buildInputs = [
        gcc49
        # go
        # ruby_2_4
        # gdb
        # swig
        # libev
        #...
    ];
  };
}
