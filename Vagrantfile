# Config
hosts_ip = '10.10.10.10'
hosts_domain_name = 'registry.vm'

def cputs(str, color=:normal)
  # Escape com " para colorir no shell.
  hash_color = {
    :normal       => "\033[0m",
    :red          => "\033[00;31m",
    :green        => "\033[00;32m",
    :yellow       => "\033[00;33m",
    :blue         => "\033[00;34m",
    :magenta      => "\033[00;35m",
    :cyan         => "\033[00;36m",
    :bold_red     => "\033[01;31m",
    :bold_green   => "\033[01;32m",
    :bold_yellow  => "\033[01;33m",
    :bold_blue    => "\033[01;34m",
    :bold_magenta => "\033[01;35m",
    :bold_cyan    => "\033[01;36m",
  }

  puts hash_color[color] + str + hash_color[:normal]
end

def os
  @os ||= (
    host_os = RbConfig::CONFIG['host_os']
    case host_os
    when /mswin|msys|mingw|cygwin|bccwin|wince|emc/
      :windows
    when /darwin|mac os/
      :macosx
    when /linux/
      :linux
    when /solaris|bsd/
      :unix
    else
      raise Error, "Unknown OS: #{host_os.inspect}"
    end
  )
end

if os.eql?(:windows)
  hosts_file = '%SystemRoot%\system32\drivers\etc\hosts'
  #hosts_file = 'C:\Windows\system32\drivers\etc\hosts'
else
  hosts_file = '/etc/hosts'
end

hosts_entry = "#{hosts_ip} #{hosts_domain_name}"
hosts_regex = Regexp.new(Regexp.escape(hosts_entry))
execute_hook_list = %w(up reload provision)

if execute_hook_list.any? { |entry| entry == ARGV.first }
  if File.readlines(hosts_file).any? { |line| line =~ hosts_regex }
    cputs "Local DNS record already set: \"#{hosts_entry}\"", :green
  else
    cputs "Configuring #{hosts_file} local DNS record: \"#{hosts_entry}\"", :green
    begin
      if os.eql?(:windows)
        File.open(hosts_file, 'a') do |file|
          file << "\n\n#{hosts_entry}\n"
        end
      else
        cputs 'You may need to type your sudo password.', :green
        system("sudo bash -c 'echo \"\n#{hosts_entry}\" >> #{hosts_file}'")
      end
      cputs 'Done!', :green
    rescue
      cputs "Couldn't edit #{hosts_file} for DNS record: \"#{hosts_entry}\"", :red
      cputs "Please do it manually.", :red
    end
  end
end

Vagrant.configure('2') do |config|
  config.vm.provider :virtualbox do |vb|
    vb.gui = false
    vb.customize ['modifyvm', :id, '--memory', 512]
    vb.customize ['modifyvm', :id, '--cpus', 2]
    vb.customize ['modifyvm', :id, '--cpuexecutioncap', 90]
  end

  config.vm.define 'registry' do |v|
    v.vm.hostname = 'registry'
    v.vm.box = 'precise64'
    v.vm.box_url = 'http://files.vagrantup.com/precise64.box'
    v.vm.network :private_network, ip: hosts_ip
  end

  config.vm.provision :shell do |s|
    s.path = 'provision/install.sh'
  end
end
